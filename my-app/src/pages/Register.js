import React, { useState, useEffect } from "react";
import { register } from "../api/ApiCalls";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useHistory } from "react-router-dom";

export default function Register() {
  const [inputFields, setInputFields] = useState({
    email: "",
    password: "",
    comfirmedPassword: "",
    firstname: "",
    lastname: "",
  });
  const [errors, setErrors] = useState({});
  const history = useHistory();

  const validateValues = (inputValues) => {
    let errors = {};
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(inputValues.email)) {
      errors.email = "Invalid email address";
    }
    if (inputValues.firstname.length < 1) {
      errors.firstname = "First Name too short";
    }
    if (inputValues.lastname.length < 1) {
      errors.lastname = "Last Name too short";
    }
    if (inputValues.password.length < 5) {
      errors.password = "Password is too short";
    }
    if (inputValues.comfirmedPassword !== inputValues.password) {
      errors.comfirmedPassword = "Not the same as password";
    }
    return errors;
  };

  const handleChange = (e) => {
    setInputFields({ ...inputFields, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    let errors = validateValues(inputFields);
    setErrors(errors);

    if (Object.keys(errors).length === 0) {
      register({
        email: inputFields.email,
        password: inputFields.password,
        lastname: inputFields.lastname,
        firstname: inputFields.firstname,
      }).then((data) => {
        console.log(data);
        toast.success("Successfully submitted ✓");
        history.push("/login");
      });
    }
  };

  return (
    <div
      className="d-flex align-items-center justify-content-center"
      style={{ minHeight: "100vh" }}
    >
      <div className="col-md-5">
        <form className="border p-4" onSubmit={handleSubmit}>
          <h3 className="text-center mb-4">Register</h3>
          <div className="container mb-1">
            <div className="row">
              <div className="col-6">
                <div>
                  <label for="firstname" className="form-label">
                    Fisrt Name
                  </label>
                </div>
                <input
                  type="text"
                  className="form-control"
                  id="firstname"
                  name="firstname"
                  onChange={handleChange}
                />
                {errors.firstname ? (
                  <p className="error text-danger">{errors.firstname}</p>
                ) : null}
              </div>
              <div className="col-6">
                <label for="lastname" className="form-label">
                  Last Name
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="lastname"
                  name="lastname"
                  onChange={handleChange}
                />
                {errors.lastname ? (
                  <p className="error text-danger">{errors.lastname}</p>
                ) : null}
              </div>
            </div>
          </div>
          <div className="mb-1 p-2">
            <label for="email" className="form-label">
              Email
            </label>
            <input
              type="text"
              className="form-control"
              id="email"
              name="email"
              onChange={handleChange}
            />
            {errors.email ? (
              <p className="error text-danger">{errors.email}</p>
            ) : null}
          </div>
          <div className="mb-1 p-2">
            <label for="password" className="form-label">
              Mot de passe
            </label>
            <input
              type="password"
              className="form-control"
              id="password"
              name="password"
              onChange={handleChange}
            />
            {errors.password ? (
              <p className="error text-danger">{errors.password}</p>
            ) : null}
          </div>
          <div className="mb-1 p-2">
            <label for="comfirmedPassword" className="form-label">
              Comfirmer Mot de passe
            </label>
            <input
              type="password"
              className="form-control"
              id="comfirmedPassword"
              name="comfirmedPassword"
              onChange={handleChange}
            />
            {errors.comfirmedPassword ? (
              <p className="error text-danger">{errors.comfirmedPassword}</p>
            ) : null}
          </div>
          <div className="text-center">
            <button type="submit" className="btn btn-primary col-3">
              Submit
            </button>
          </div>
          <div className="text-center m-2">
            <a href="/login">Vous avez un compte ?</a>
          </div>
        </form>
        <ToastContainer />
      </div>
    </div>
  );
}
