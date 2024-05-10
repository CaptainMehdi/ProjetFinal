import {
  BrowserRouter as Router,
  Route,
  Navigate,
  Routes,
} from "react-router-dom";
import Login from "../pages/Login";
import Register from "../pages/Register";
import NotFoundPage from "../pages/NotFoundPage";
import HomePage from "../pages/HomePage";
import React, { useState, useEffect } from "react";
import { isAuthenticated } from "../api/ApiCalls";
import InscriptionCours from "../pages/InscriptionCours";

export const AppRoutes = () => {
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    setAuthenticated(isAuthenticated());
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />} />
        <Route path="/inscrire/:userId" element={<InscriptionCours />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/home/:userId"
          element={
            isAuthenticated() ? <HomePage /> : <Navigate to="/login" replace />
          }
        />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </Router>
  );
};
