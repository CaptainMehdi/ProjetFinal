import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowRightFromBracket } from "@fortawesome/free-solid-svg-icons";
import { setAuthenticated } from "../api/ApiCalls";
import { useNavigate, useParams } from "react-router-dom";
import { getUserById } from "../api/ApiCalls";

export default function Navbar() {
  const { userId } = useParams();
  const [user, setUser] = useState();

  useEffect(() => {
    console.log("User ID:", userId);
  }, [userId]);

  useEffect(() => {
    getUser();
  }, []);

  const getUser = async () => {
    await getUserById(userId).then((data) => {
      console.log(data);
      if (data) {
        setUser(data);
      } else {
        setUser(data);
      }
    });
  };

  const navigate = useNavigate();

  const handleLogOut = () => {
    setAuthenticated(false);
    navigate(`/login`);
    window.location.reload();
  };

  const goToInscription = () => {
    navigate(`/inscrire/${userId}`);
    window.location.reload();
  };

  const gotoHome = () => {
    navigate(`/home/${userId}`);
    window.location.reload();
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
        <div className="container-fluid">
          <div className="row w-100 justify-content-between align-items-center">
            <div className="col-auto">
              <button
                onClick={gotoHome}
                className="navbar-brand mx-3 btn btn-transparent"
              >
                {user
                  ? `Bienvenue ${user.lastname} ${user.firstname}`
                  : "Bienvenue"}
              </button>
            </div>
            <div className="col-auto">
              <div className="links d-flex justify-content-end">
                <div className="col">
                  <button
                    onClick={goToInscription}
                    className="btn btn-tranparent text-white m-2"
                    style={{ textDecoration: "none" }}
                    onMouseEnter={(e) => {
                      e.target.classList.add("bg-light");
                      e.target.classList.remove("text-white");
                    }}
                    onMouseLeave={(e) => {
                      e.target.classList.remove("bg-light");
                      e.target.classList.add("text-white");
                    }}
                  >
                    M'inscrire a un cours
                  </button>
                </div>
                <div className="col-auto">
                  <button className="btn btn-danger m-2" onClick={handleLogOut}>
                    <FontAwesomeIcon icon={faArrowRightFromBracket} />
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </>
  );
}
