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
      throw new Error("Failed to register");
    }
    const data = await response.json();
    console.log("Register successful:", data);
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

export const getUserById = async (id) => {
  try {
    const response = await fetch("http://localhost:8080/api/getUser/" + id);
    if (!response.ok) {
      throw new Error("Failed to fetch user");
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching user:", error);
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
    const response = await fetch("http://localhost:8080/api/horaire/all");
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
      throw new Error("Failed to save horaire");
    }
    const data = await response.json();
    console.log("Horaire saved successful:", data);
    return data;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};

export const getBassinEnums = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/enumBassin");
    if (!response.ok) {
      throw new Error("Failed to fetch enums");
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching enums:", error);
    throw error;
  }
};

export const getTypeEnums = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/enumType");
    if (!response.ok) {
      throw new Error("Failed to fetch enums");
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching enums:", error);
    throw error;
  }
};

export const getFileExcelHoraire = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/horaire/excel");

    if (!response.ok) {
      throw new Error("Failed to Create Excel File");
    }
    const data = await response.json();
    console.log("FichierDto received:", data);
    return data;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};

export const getAllProf = async () => {
  try {
    const response = await fetch("http://localhost:8080/api/teachers");

    if (!response.ok) {
      throw new Error("Failed to Get Teachers");
    }
    const data = await response.json();
    console.log("Teachers received:", data);
    return data;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};

export const getAllNiveaux = async (bassin) => {
  try {
    const response = await fetch(
      "http://localhost:8080/api/niveau/all/" + bassin
    );

    if (!response.ok) {
      throw new Error("Failed to Get Niveau");
    }
    const data = await response.json();
    console.log("Niveau received:", data);
    return data;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};

export const saveCours = async (cours) => {
  try {
    const response = await fetch("http://localhost:8080/api/cours/save", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cours),
    });

    if (!response.ok) {
      throw new Error("Failed to save Cours");
    }
    const data = await response.json();
    console.log("Cours saved successful:", data);
    return data;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};

export const getBainsLibreId = async (bassin) => {
  try {
    const response = await fetch(
      "http://localhost:8080/api/activity/get/" + bassin
    );

    if (!response.ok) {
      throw new Error("Failed to Get Niveau");
    }
    const data = await response.json();
    console.log("Niveau received:", data);
    return data;
  } catch (error) {
    console.error("Error:", error.message);
    throw error;
  }
};
