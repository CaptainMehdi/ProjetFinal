import React from "react";

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <a className="navbar-brand mx-3" href="#">
        Home
      </a>
      <div className="d-flex flex-grow-1 justify-content-end">
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto"></ul>
        </div>
      </div>
    </nav>
  );
}
