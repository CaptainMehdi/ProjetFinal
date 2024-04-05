import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../components/NavBar";
import Horaire from "./Horaire";

export default function HomePage() {
  const { userId } = useParams();

  useEffect(() => {
    console.log("User ID:", userId);
  }, [userId]);

  return (
    <div>
      <Navbar />
      <Horaire />
    </div>
  );
}
