import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../components/NavBar";
import Horaire from "./Horaire";
import { getUserById } from "../api/ApiCalls";

export default function HomePage() {
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

  return (
    <div>
      <Navbar user={user} />
      <Horaire user={user} />
    </div>
  );
}
