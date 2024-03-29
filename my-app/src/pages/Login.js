import React, { useState } from "react";
import { login } from "../api/ApiCalls";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    login({ email, password }).then((response) => {
      console.log("Response: " + response);
    });
  };

  return (
    <div
      className="d-flex align-items-center justify-content-center"
      style={{ minHeight: "100vh" }}
    >
      <div className="col-md-4">
        <form className="border p-4" onSubmit={handleSubmit}>
          <h3 className="text-center mb-4">Login</h3>
          <div className="mb-3">
            <label for="email" className="form-label">
              Email
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label for="mdp" className="form-label">
              Mot de passe
            </label>
            <input
              type="mdp"
              className="form-control"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <div className="text-center">
            <button type="submit" className="btn btn-primary col-3">
              Submit
            </button>
          </div>
          <div className="text-center m-2">
            <a href="/register">Vous n'avez pas de compte ?</a>
          </div>
        </form>
      </div>
    </div>
  );
}
