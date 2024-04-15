import React from "react";

export default function Navbar() {
  return (
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <a class="navbar-brand mx-3" href="#">
        Home
      </a>
      <div class="d-flex flex-grow-1 justify-content-end">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto"></ul>
        </div>
      </div>
    </nav>
  );
}