import React from "react";

const ScheduleForm = ({
  bassin,
  selectedBassin,
  selectedSections,
  selectedTimeFrom,
  selectedTimeTo,
  handleBassinChange,
  handleAddScheduleItem,
  handleSectionToggle,
  setSelectedTimeFrom,
  setSelectedTimeTo,
  handleActivityClick,
  activities,
  selectedActivity,
  days,
  handleDays,
  selectedDay,
  selectedTeacher,
  handleTeacherClick,
  teachers,
  selectedNiveau,
  handleNiveauClick,
  niveaux,
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
          <div className="row">
            <div className="col">
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
            <div className="col">
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
          </div>

          <div>
            <label>Choisir une activites:</label>
            <select
              className="form-select form-select-sm mb-3"
              aria-label=".form-select-lg example"
              value={selectedActivity || ""}
              onChange={(e) => {
                handleActivityClick(e.target.value);
              }}
            >
              <option value="" disabled>
                Select Activity
              </option>
              {activities.map((activity, index) => (
                <option key={index} value={activity.id}>
                  {activity}
                </option>
              ))}
            </select>
          </div>

          {selectedActivity === "cours" && (
            <div>
              <div>
                <label className="form-label">Moniteur</label>
                <select
                  className="form-select form-select-sm mb-3"
                  aria-label=".form-select-lg example"
                  value={selectedTeacher ? JSON.stringify(selectedTeacher) : ""}
                  onChange={(e) => {
                    const selectedTeacher = JSON.parse(e.target.value);
                    console.log(selectedTeacher);
                    handleTeacherClick(selectedTeacher);
                  }}
                >
                  <option value="" disabled>
                    Selectionner un professeur
                  </option>
                  {teachers.map((teacher, index) => (
                    <option key={index} value={JSON.stringify(teacher)}>
                      {teacher.lastname}
                    </option>
                  ))}
                </select>
              </div>
              <div>
                <label className="form-label">Niveau</label>
                <select
                  className="form-select form-select-sm mb-3"
                  aria-label=".form-select-lg example"
                  value={selectedNiveau ? JSON.stringify(selectedNiveau) : ""}
                  onChange={(e) => {
                    const selectedNiveau = JSON.parse(e.target.value);
                    console.log(selectedNiveau);
                    handleNiveauClick(selectedNiveau);
                  }}
                >
                  <option value="" disabled>
                    Selectionner un niveau
                  </option>
                  {niveaux.map((niveau, index) => (
                    <option key={index} value={JSON.stringify(niveau)}>
                      {niveau.nom} ({niveau.bassin})
                    </option>
                  ))}
                </select>
              </div>
            </div>
          )}

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
