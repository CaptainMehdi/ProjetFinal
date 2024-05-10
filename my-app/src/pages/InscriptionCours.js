import Navbar from "../components/NavBar";
import React, { useEffect } from "react";
import { useParams } from "react-router-dom";

export default function InscriptionCours() {
  const { userId } = useParams();

  useEffect(() => {
    console.log("User ID:", userId);
  }, [userId]);

  return (
    <>
      <Navbar></Navbar>
      <div className="container">
        <div>
          <h2 className="my-4 text-center">Inscription</h2>
          {/* <table className="table">
            <thead className="thead-primary">
              <tr>
                <th scope="col">Niveau</th>
                <th scope="col">Bassin</th>
                <th scope="col">De</th>
                <th scope="col">A</th>
                <th scope="col">Maximum</th>
                <th scope="col">Vous inscrire</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="row">1</th>
                <th>nhio</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>lop</td>
                <td>
                  <button className="btn btn-primary">oui</button>
                </td>
              </tr>
            </tbody>
          </table> */}
          <div className="text-center">À venir !</div>
        </div>
      </div>
    </>
  );
}
