import React, { useState, useEffect } from "react";
import ScheduleGrid from "../components/ScheduleGrid";
import ScheduleForm from "../components/ScheduleForm";
import { getAllActivities, getEnumsValues, saveHoraire } from "../api/ApiCalls";
import Activity from "../models/Activity";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import HoraireGrid from "../models/HoraireGrid";

export default function Horaire() {
  const [bassin, setBassin] = useState([]);
  const [selectedBassin, setSelectedBassin] = useState();
  const [selectedSections, setSelectedSections] = useState([]);
  const [selectedTimeFrom, setSelectedTimeFrom] = useState("");
  const [selectedTimeTo, setSelectedTimeTo] = useState("");
  const [schedule, setSchedule] = useState([]);
  const [scheduleName, setScheduleName] = useState("");
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
    return getEnumsValues();
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
      scheduleName &&
      selectedActivity &&
      selectedBassin
    ) {
      const overlappingItem = schedule.find(
        (item) =>
          item.longueur.some((section) => selectedSections.includes(section)) &&
          ((item.from >= selectedTimeFrom && item.from <= selectedTimeTo) ||
            (item.to >= selectedTimeFrom && item.to <= selectedTimeTo) ||
            (selectedTimeFrom >= item.from && selectedTimeFrom <= item.to) ||
            (selectedTimeTo >= item.from && selectedTimeTo <= item.to)) &&
          item.bassin == selectedBassin
      );

      if (overlappingItem) {
        toast.error(
          "Il y a déjà un élément planifié qui se chevauche avec cette plage horaire et ces sections."
        );
        return;
      }

      const newItem = new HoraireGrid({
        name: scheduleName,
        from: selectedTimeFrom,
        to: selectedTimeTo,
        activitePiscineId: selectedActivity,
        bassin: selectedBassin,
        longueur: selectedSections,
      });

      console.log(newItem);

      await saveHoraire(newItem).then((data) => {
        if (data) {
          toast.success("Successfully added ✓");
          setSchedule([...schedule, newItem]);
          setSelectedSections([]);
          setSelectedTimeFrom("");
          setSelectedTimeTo("");
          setScheduleName("");
          setselectedActivity("");
          setSelectedBassin("");
        } else {
          toast.error("Error lors de la sauvegarde");
        }
      });
    } else {
      toast.error(
        "Veuillez sélectionner des sections, des heures et un nom avant d'ajouter au planning."
      );
    }
  };

  // ACTIVITIES
  useEffect(() => {
    fetchActivitiesFromMethod()
      .then((data) => {
        const activityList = createActivityList(data);
        setActivities(activityList);
      })
      .catch((error) => console.error("Error fetching options:", error));
  }, []);

  const createActivityList = (data) => {
    return data.map((item) => {
      return new Activity(item.id, item.nom, item.bassin);
    });
  };

  const fetchActivitiesFromMethod = async () => {
    return getAllActivities();
  };

  const handleActivityClick = (option) => {
    setselectedActivity(option);
  };

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
              Ajout Cours
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
                scheduleName={scheduleName}
                handleBassinChange={handleBassinChange}
                handleSectionToggle={handleSectionToggle}
                handleAddScheduleItem={handleAddScheduleItem}
                setSelectedTimeFrom={setSelectedTimeFrom}
                setSelectedTimeTo={setSelectedTimeTo}
                setScheduleName={setScheduleName}
                handleActivityClick={handleActivityClick}
                activities={activities}
                selectedActivity={selectedActivity}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="my-2">
        <ScheduleGrid schedule={schedule} bassin={bassin} />
      </div>
      <ToastContainer />
    </div>
  );
}
