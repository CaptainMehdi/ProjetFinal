import React, { useState, useEffect } from "react";
import ScheduleGrid from "../components/ScheduleGrid";
import ScheduleForm from "../components/ScheduleForm";
import {
  getAllActivities,
  getBassinEnums,
  getTypeEnums,
  saveHoraire,
  getFileExcelHoraire,
  getAllHoraire,
  getAllProf,
  getAllNiveaux,
  saveCours,
} from "../api/ApiCalls";
import Activity from "../models/Activity";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import HoraireGrid from "../models/HoraireGrid";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFileExcel } from "@fortawesome/free-solid-svg-icons";

export default function Horaire() {
  const [bassin, setBassin] = useState([]);
  const [selectedBassin, setSelectedBassin] = useState();
  const [selectedSections, setSelectedSections] = useState([]);
  const [selectedTimeFrom, setSelectedTimeFrom] = useState("");
  const [selectedTimeTo, setSelectedTimeTo] = useState("");
  const [schedule, setSchedule] = useState([]);
  const [activities, setActivities] = useState([]);
  const [selectedActivity, setselectedActivity] = useState();

  // BASSIN
  const handleBassinChange = (option) => {
    setSelectedBassin(option);
    setSelectedSections([]);
    setselectedActivity("");
  };

  useEffect(() => {
    fetchEnumsBassin()
      .then((data) => {
        setBassin(data);
      })
      .catch((error) => console.error("Error fetching options:", error));
  }, []);

  const fetchEnumsBassin = async () => {
    return getBassinEnums();
  };

  //SECTION

  const handleSectionToggle = (section) => {
    if (selectedSections.includes(section)) {
      setSelectedSections(selectedSections.filter((s) => s !== section));
    } else {
      setSelectedSections([...selectedSections, section]);
    }
  };

  //HORAIRE AJOUT
  const handleAddScheduleItem = async (event) => {
    event.preventDefault();

    if (
      selectedSections.length > 0 &&
      selectedTimeFrom &&
      selectedTimeTo &&
      selectedActivity &&
      selectedBassin &&
      selectedDay &&
      selectedTimeFrom != selectedTimeTo
    ) {
      const overlappingItem = schedule.find(
        (item) =>
          item.longueur.some((section) => selectedSections.includes(section)) &&
          ((item.from >= selectedTimeFrom && item.from <= selectedTimeTo) ||
            (item.to >= selectedTimeFrom && item.to <= selectedTimeTo) ||
            (selectedTimeFrom >= item.from && selectedTimeFrom <= item.to) ||
            (selectedTimeTo >= item.from && selectedTimeTo <= item.to)) &&
          item.bassin == selectedBassin &&
          item.day == selectedDay
      );

      if (overlappingItem) {
        toast.error(
          "Il y a déjà un élément planifié qui se chevauche avec cette plage horaire et ces sections."
        );
        return;
      }

      if (selectedActivity === "cours") {
        const cours = { prof: selectedTeacher, niveau: selectedNiveau };
        const id = await saveCours(cours);

        const newItem = new HoraireGrid({
          from: selectedTimeFrom,
          to: selectedTimeTo,
          activitePiscineId: id,
          bassin: selectedBassin,
          longueur: selectedSections,
          day: selectedDay,
        });

        await saveHoraire(newItem).then((data) => {
          if (data) {
            toast.success("Successfully added ✓");
            setSchedule([...schedule, newItem]);
            setSelectedSections([]);
            setSelectedTimeFrom("");
            setSelectedTimeTo("");
            setselectedActivity("");
            setSelectedBassin("");
            setSelectedDay("");
            setSelectedTeacher("");
            setSelectedNiveau("");
          } else {
            toast.error("Error lors de la sauvegarde");
          }
        });
      }
    } else {
      toast.error(
        "Veuillez remplir le formulaire avant d'ajouter au planning."
      );
    }
  };

  // ACTIVITIES

  useEffect(() => {
    getTypeEnums()
      .then((data) => {
        console.log(data);
        setActivities(data);
      })
      .catch((error) => console.error("Error fetching options:", error));
  }, []);

  const handleActivityClick = (option) => {
    setselectedActivity(option);
  };

  //Days
  //GRID
  const days = [
    "lundi",
    "mardi",
    "mercredi",
    "jeudi",
    "vendredi",
    "samedi",
    "dimanche",
  ];

  const [day, setDay] = useState(days[0]);
  const [index, setIndex] = useState(0);

  const handleNext = () => {
    setIndex((prevIndex) => (prevIndex + 1) % days.length);
    setDay(days[index]);
  };

  const handlePrev = () => {
    setIndex((prevIndex) => (prevIndex - 1 + days.length) % days.length);
    setDay(days[index]);
  };

  useEffect(() => {
    setDay(days[index]);
  }, [index]);

  //FORM
  const [selectedDay, setSelectedDay] = useState();

  const handleDays = (option) => {
    setSelectedDay(option);
  };

  //FILE EXCEL

  const downloadExcelFile = async () => {
    try {
      const data = await getFileExcelHoraire();

      const binaryData = atob(data.data);

      const arrayBuffer = new ArrayBuffer(binaryData.length);
      const uint8Array = new Uint8Array(arrayBuffer);
      for (let i = 0; i < binaryData.length; i++) {
        uint8Array[i] = binaryData.charCodeAt(i);
      }

      const blob = new Blob([uint8Array], { type: "application/octet-stream" });

      const blobUrl = URL.createObjectURL(blob);

      const link = document.createElement("a");
      link.href = blobUrl;
      link.download = data.nom;
      document.body.appendChild(link);

      link.click();

      document.body.removeChild(link);
      URL.revokeObjectURL(blobUrl);
    } catch (error) {
      toast.error("Une erreur est survenue lors du telechargement Excel");
      console.error("Error downloading Excel file:", error);
    }
  };

  //TEACHER
  const [selectedTeacher, setSelectedTeacher] = useState("");
  const [teachers, setTeachers] = useState([]);

  const handleTeacherClick = (option) => {
    setSelectedTeacher(option);
  };

  useEffect(() => {
    getAllProf()
      .then((data) => {
        console.log(data);
        setTeachers(data);
      })
      .catch((error) => console.error("Error fetching options:", error));
  }, []);

  //NIVEAU
  const [selectedNiveau, setSelectedNiveau] = useState("");
  const [niveaux, setNiveaux] = useState([]);

  const handleNiveauClick = (option) => {
    setSelectedNiveau(option);
  };

  useEffect(() => {
    if (selectedBassin) {
      getAllNiveaux(selectedBassin)
        .then((data) => {
          console.log(data);
          setNiveaux(data);
        })
        .catch((error) => console.error("Error fetching options:", error));
    }
  }, [selectedBassin]);

  //GetHoraire
  useEffect(() => {
    getAllHoraire()
      .then((data) => {
        console.log(data);
        setSchedule(data);
      })
      .catch((error) => console.error("Error fetching options:", error));
  }, []);

  return (
    <div className="container">
      <div className="accordion accordion-flush" id="accordionFlushExample">
        <div className="accordion-item">
          <h2 className="accordion-header">
            <button
              className="accordion-button collapsed rounded border my-2"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#flush-collapseForm"
              aria-expanded="false"
              aria-controls="flush-collapseForm"
            >
              Ajout Activitees
            </button>
          </h2>
          <div
            id="flush-collapseForm"
            className="accordion-collapse collapse bg-light"
            data-bs-parent="#accordionFlushExample"
          >
            <div className="accordion-body">
              <ScheduleForm
                bassin={bassin}
                selectedBassin={selectedBassin}
                selectedSections={selectedSections}
                selectedTimeFrom={selectedTimeFrom}
                selectedTimeTo={selectedTimeTo}
                handleBassinChange={handleBassinChange}
                handleSectionToggle={handleSectionToggle}
                handleAddScheduleItem={handleAddScheduleItem}
                setSelectedTimeFrom={setSelectedTimeFrom}
                setSelectedTimeTo={setSelectedTimeTo}
                handleActivityClick={handleActivityClick}
                activities={activities}
                selectedActivity={selectedActivity}
                days={days}
                handleDays={handleDays}
                selectedDay={selectedDay}
                selectedTeacher={selectedTeacher}
                handleTeacherClick={handleTeacherClick}
                teachers={teachers}
                selectedNiveau={selectedNiveau}
                handleNiveauClick={handleNiveauClick}
                niveaux={niveaux}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="my-2">
        <ScheduleGrid
          schedule={schedule}
          bassin={bassin}
          handleNext={handleNext}
          handlePrev={handlePrev}
          day={day}
        />
      </div>
      <div>
        <button
          className="btn btn-success btn-lg w-100 my-2"
          onClick={downloadExcelFile}
        >
          <span className="mx-2">Télécharger</span>
          <FontAwesomeIcon icon={faFileExcel} />
        </button>
      </div>
      <ToastContainer />
    </div>
  );
}
