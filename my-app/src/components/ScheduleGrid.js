import React from "react";

const ScheduleGrid = ({ schedule, bassin }) => {
  const generateTimeIntervals = () => {
    const timeIntervals = [];
    for (let hour = 7; hour <= 20; hour++) {
      for (let minute = 0; minute < 60; minute += 15) {
        const time = `${hour.toString().padStart(2, "0")}:${minute
          .toString()
          .padStart(2, "0")}`;
        timeIntervals.push(time);
      }
    }
    return timeIntervals;
  };

  const timeIntervals = generateTimeIntervals();

  return (
    <>
      <div className="container">
        <div className="row">
          <h3 className="text-center">Lundi</h3>
          <div className="col-1 border">Temps</div>
          <div className="col-6 border">
            <div className="container">
              <div className="row">
                {[...Array(6)].map((_, index) => (
                  <div key={index} className="col">
                    Section {index + 1}
                  </div>
                ))}
              </div>
            </div>
          </div>
          <div className="col-5 border">
            <div className="container">
              <div className="row">
                {[...Array(4)].map((_, index) => (
                  <div key={index} className="col">
                    Section {index + 1}
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="container">
        {timeIntervals.map((time, index) => (
          <div key={index} className="row">
            <div className="col-1 border text-center">{time}</div>
            <div className="col-6 border p-0">
              <div className="container-fluid">
                <div className="row">
                  {[...Array(6)].map((_, sectionIndex) => {
                    if (schedule && schedule.length > 0) {
                      const matchingScheduleItems = schedule.filter(
                        (item) =>
                          item.bassin == bassin[0] &&
                          item.longueur.includes(sectionIndex + 1) &&
                          item.from <= time &&
                          item.to >= time
                      );

                      if (matchingScheduleItems.length > 0) {
                        return (
                          <div key={sectionIndex} className="col p-0">
                            {matchingScheduleItems.map((item, idx) => (
                              <div key={idx} className="schedule-item">
                                {item.name}
                              </div>
                            ))}
                          </div>
                        );
                      } else {
                        return (
                          <div key={sectionIndex} className="col p-0"></div>
                        );
                      }
                    }
                  })}
                </div>
              </div>
            </div>
            <div className="col-5 border">
              <div className="container-fluid">
                <div className="row">
                  {[...Array(4)].map((_, sectionIndex) => {
                    if (schedule && schedule.length > 0) {
                      const matchingScheduleItems = schedule.filter(
                        (item) =>
                          item.bassin == bassin[1] &&
                          item.longueur.includes(sectionIndex + 1) &&
                          item.from <= time &&
                          item.to >= time
                      );

                      if (matchingScheduleItems.length > 0) {
                        return (
                          <div key={sectionIndex} className="col p-0">
                            {matchingScheduleItems.map((item, idx) => (
                              <div key={idx} className="schedule-item">
                                {item.name}
                              </div>
                            ))}
                          </div>
                        );
                      } else {
                        return (
                          <div key={sectionIndex} className="col p-0"></div>
                        );
                      }
                    }
                  })}
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default ScheduleGrid;
