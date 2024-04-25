import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowRightFromBracket } from "@fortawesome/free-solid-svg-icons";
import { setAuthenticated } from "../api/ApiCalls";
import { useHistory } from "react-router-dom";

export default function Navbar({ user }) {
  const history = useHistory();

  const handleLogOut = () => {
    setAuthenticated(false);
    history.push(`/login`);
    window.location.reload();
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <a className="navbar-brand mx-3">
        {user ? `Bienvenue ${user.lastname} ${user.firstname}` : "Bienvenue"}
      </a>
      <div className="d-flex flex-grow-1 justify-content-end">
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto"></ul>
        </div>
      </div>
      <button className="btn btn-danger m-2" onClick={handleLogOut}>
        <FontAwesomeIcon icon={faArrowRightFromBracket} />
      </button>
    </nav>
  );
}
