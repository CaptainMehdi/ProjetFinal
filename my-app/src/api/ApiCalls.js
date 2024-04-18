export const register = async (userData) => {
  try {
    const response = await fetch("http://localhost:8080/api/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userData),
    });

    if (!response.ok) {
      throw new Error("Failed to login");
    }
    const data = await response.json();
    console.log("Login successful:", data);
    return data;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};

export const login = async (userData) => {
  try {
    const response = await fetch("http://localhost:8080/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userData),
    });

    if (!response.ok) {
      throw new Error("Failed to login");
    }
    const data = await response.json();

    return data;
  } catch (error) {
    throw error;
  }
};

export const isAuthenticated = () => {
  const authenticated = localStorage.getItem("authenticated");
  return authenticated === "true";
};

export const setAuthenticated = (value) => {
  localStorage.setItem("authenticated", value ? "true" : "false");
};

export const getAllActivities = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/activity/all");
    if (!response.ok) {
      throw new Error("Failed to fetch activities");
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching activities:", error);
    throw error;
  }
};

export const getAllHoraire = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/activity/all");
    if (!response.ok) {
      throw new Error("Failed to fetch activities");
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching activities:", error);
    throw error;
  }
};

export const saveHoraire = async (horaire) => {
  try {
    const response = await fetch("http://localhost:8080/api/horaire/save", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(horaire),
    });

    if (!response.ok) {
      throw new Error("Failed to login");
    }
    const data = await response.json();
    console.log("Login successful:", data);
    return data;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};
