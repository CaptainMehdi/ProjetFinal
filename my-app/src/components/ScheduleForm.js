import React from "react";

const ScheduleForm = ({
  bassin,
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

  return (
    <>
      <form onSubmit={handleAddScheduleItem}>
        <div className="container">
          <div>
            <label>Bassin</label>
            <select
              className="form-select form-select-sm mb-3"
              aria-label=".form-select-lg example"
              value={bassin}
              onChange={handleBassinChange}
            >
              <option value="Grand">Grand</option>
              <option value="Petit">Petit</option>
            </select>
          </div>
          <div>
            <label>Nombre de section</label>
            {[...Array(bassin === "Grand" ? 6 : 4)].map((_, index) => (
              <div key={index} className="form-check">
                <input
                  className="form-check-input"
                  type="checkbox"
                  id={`section${index}`}
                  checked={selectedSections.includes(index + 1)}
                  onChange={() => handleSectionToggle(index + 1)}
                />
                <label className="form-check-label" htmlFor={`section${index}`}>
                  Section {index + 1}
                </label>
              </div>
            ))}
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
            <label className="form-label">Nom</label>
            <input
              type="text"
              className="form-control"
              value={scheduleName}
              onChange={(e) => setScheduleName(e.target.value)}
            />
          </div>
          <div>
            <label>Choisir une activites:</label>
            <select
              className="form-select form-select-sm mb-3"
              aria-label=".form-select-lg example"
              onChange={handleActivityClick}
            >
              {activities
                .filter((activity) => {
                  console.log("bassin:", bassin);
                  console.log("activity.bassin:", activity.bassin);
                  console.log(activity.bassin == bassin);
                  return activity.bassin == bassin;
                })
                .map((activity, index) => (
                  <option
                    key={index}
                    onClick={() => handleActivityClick(activity)}
                  >
                    {activity.nom}
                  </option>
                ))}
            </select>
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
