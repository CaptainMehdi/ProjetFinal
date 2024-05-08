import React from "react";

const ScheduleForm = ({
  bassin,
  selectedBassin,
  selectedSections,
  selectedTimeFrom,
  selectedTimeTo,
  scheduleName,
  handleBassinChange,
  handleAddScheduleItem,
  handleSectionToggle,
  setSelectedTimeFrom,
  setSelectedTimeTo,
  setScheduleName,
  handleActivityClick,
  activities,
  selectedActivity,
  days,
  handleDays,
  selectedDay,
}) => {
  const generateTimeOptions = (minHour, minMinute) => {
    const options = [];

    for (let hour = minHour; hour <= 21; hour++) {
      const startMinute = hour === minHour ? minMinute : 0;
      for (let minute = startMinute; minute < 60; minute += 15) {
        const timeString = `${hour.toString().padStart(2, "0")}:${minute
          .toString()
          .padStart(2, "0")}`;
        options.push(
          <option key={timeString} value={timeString}>
            {timeString}
          </option>
        );
      }
    }
    return options;
  };
  const containsNageur =
    selectedActivity && selectedActivity.includes("nageur");

  return (
    <>
      <form onSubmit={handleAddScheduleItem}>
        <div className="container">
          <div>
            <label>Jour de la semaine</label>
            <select
              className="form-select form-select-sm mb-3"
              aria-label=".form-select-lg example"
              onChange={(e) => handleDays(e.target.value)}
              value={selectedDay || ""}
            >
              <option value="" disabled>
                Choisir un jour
              </option>
              {days.map((item, index) => (
                <option key={index} value={item}>
                  {item}
                </option>
              ))}
            </select>
          </div>
          <div>
            <label>Bassin</label>
            <select
              className="form-select form-select-sm mb-3"
              aria-label=".form-select-lg example"
              onChange={(e) => handleBassinChange(e.target.value)}
              value={selectedBassin || ""}
            >
              <option value="" disabled>
                Select Bassin
              </option>
              {bassin.map((item, index) => (
                <option key={index} value={item}>
                  {item}
                </option>
              ))}
            </select>
          </div>
          <div>
            <label>Nombre de section</label>
            {[...Array(selectedBassin === bassin[0] ? 6 : 4)].map(
              (_, index) => (
                <div key={index} className="form-check">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    id={`section${index}`}
                    checked={selectedSections.includes(index + 1)}
                    onChange={() => handleSectionToggle(index + 1)}
                  />
                  <label
                    className="form-check-label"
                    htmlFor={`section${index}`}
                  >
                    Section {index + 1}
                  </label>
                </div>
              )
            )}
          </div>
          <div>
            <label>De</label>
            <select
              className="form-select form-select-sm mb-3"
              aria-label=".form-select-lg example"
              value={selectedTimeFrom}
              onChange={(e) => {
                setSelectedTimeFrom(e.target.value);
                if (selectedTimeTo < e.target.value) {
                  setSelectedTimeTo(e.target.value);
                }
              }}
            >
              {generateTimeOptions(7, 0)}
            </select>
          </div>
          <div>
            <label>À</label>
            <select
              className="form-select form-select-sm mb-3"
              aria-label=".form-select-lg example"
              value={selectedTimeTo}
              onChange={(e) => setSelectedTimeTo(e.target.value)}
            >
              {generateTimeOptions(
                parseInt(selectedTimeFrom.split(":")[0]),
                parseInt(selectedTimeFrom.split(":")[1]) + 15
              )}
            </select>
          </div>
          <div>
            <label>Choisir une activites:</label>
            <select
              className="form-select form-select-sm mb-3"
              aria-label=".form-select-lg example"
              value={selectedActivity || ""}
              onChange={(e) => {
                const selectedActivity = JSON.parse(e.target.value);
                handleActivityClick(selectedActivity);
              }}
            >
              <option value="" disabled>
                Select Activity
              </option>
              {activities
                .filter((activity) => activity.bassin === selectedBassin)
                .map((activity, index) => (
                  <option key={index} value={activity.id}>
                    {activity.nom} ({activity.bassin})
                  </option>
                ))}
            </select>
          </div>
          <div>
            <div>
              <label className="form-label">Moniteur</label>
              <input
                type="text"
                className="form-control"
                value={scheduleName}
                onChange={(e) => setScheduleName(e.target.value)}
              />
            </div>
          </div>
          <div>
            <button type="submit" className="btn btn-primary my-3">
              Ajouter
            </button>
          </div>
        </div>
      </form>
    </>
  );
};

export default ScheduleForm;
