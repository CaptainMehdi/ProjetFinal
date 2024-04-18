import React, { useState, useEffect } from "react";
import ScheduleGrid from "../components/ScheduleGrid";
import ScheduleForm from "../components/ScheduleForm";
import { getAllActivities } from "../api/ApiCalls";
import Activity from "../models/Activity";

export default function Horaire() {
  const [bassin, setBassin] = useState("Grand");
  const [selectedSections, setSelectedSections] = useState([]);
  const [selectedTimeFrom, setSelectedTimeFrom] = useState("");
  const [selectedTimeTo, setSelectedTimeTo] = useState("");
  const [schedule, setSchedule] = useState([]);
  const [scheduleName, setScheduleName] = useState("");
  const [activities, setActivities] = useState([]);
  const [selectedActivity, setselectedActivity] = useState();

  const handleBassinChange = (event) => {
    setBassin(event.target.value);
    setSelectedSections([]);
  };

  const handleSectionToggle = (section) => {
    if (selectedSections.includes(section)) {
      setSelectedSections(selectedSections.filter((s) => s !== section));
    } else {
      setSelectedSections([...selectedSections, section]);
    }
  };

  const handleAddScheduleItem = (event) => {
    event.preventDefault();
    if (
      selectedSections.length > 0 &&
      selectedTimeFrom &&
      selectedTimeTo &&
      scheduleName
    ) {
      const overlappingItem = schedule.find(
        (item) =>
          item.sections.some((section) => selectedSections.includes(section)) &&
          ((item.timeFrom >= selectedTimeFrom &&
            item.timeFrom <= selectedTimeTo) ||
            (item.timeTo >= selectedTimeFrom &&
              item.timeTo <= selectedTimeTo) ||
            (selectedTimeFrom >= item.timeFrom &&
              selectedTimeFrom <= item.timeTo) ||
            (selectedTimeTo >= item.timeFrom &&
              selectedTimeTo <= item.timeTo)) &&
          item.bassin == bassin
      );

      if (overlappingItem) {
        alert(
          "Il y a déjà un élément planifié qui se chevauche avec cette plage horaire et ces sections."
        );
        return;
      }

      const newItem = {
        bassin: bassin,
        sections: selectedSections,
        timeFrom: selectedTimeFrom,
        timeTo: selectedTimeTo,
        name: scheduleName,
      };
      setSchedule([...schedule, newItem]);
      setSelectedSections([]);
      setSelectedTimeFrom("");
      setSelectedTimeTo("");
      setScheduleName("");
    } else {
      alert(
        "Veuillez sélectionner des sections, des heures et un nom avant d'ajouter au planning."
      );
    }
  };

  useEffect(() => {
    fetchOptionsFromMethod()
      .then((data) => {
        const activityList = createActivityList(data);
        setActivities(activityList);
        console.log(activityList);
      })
      .catch((error) => console.error("Error fetching options:", error));
  }, []);

  const createActivityList = (data) => {
    return data.map((item) => {
      return new Activity(item.id, item.nom, item.bassin);
    });
  };

  const fetchOptionsFromMethod = async () => {
    return getAllActivities();
  };

  const handleActivityClick = (option) => {
    selectedActivity(option);
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
        <ScheduleGrid schedule={schedule} />
      </div>
    </div>
  );
}
