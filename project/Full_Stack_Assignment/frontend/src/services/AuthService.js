import { makeRequest } from "./AxiosTemplate";

export const loginUser = async (credentials, baseURL) => {
  return makeRequest(
    "POST",
    "/api/auth/login",
    {},
    {},
    {
      email: credentials.email,
      password: credentials.password,
    },
    baseURL
  );
};

export const registerUser = async (userData, baseURL) => {
  return makeRequest(
    "POST",
    "/api/auth/register",
    {},
    {},
    {
      fullName: userData.fullName,
      email: userData.email,
      password: userData.password,
      username: userData.username,
      phone: userData.phone,
      gender: userData.gender,
      age: userData.age,
      role: "ROLE_USER",
    },
    baseURL
  );
};

export const logoutUser = async (baseURL) => {
  try {
    await makeRequest("POST", "/api/auth/logout", {}, {}, {}, baseURL);

    localStorage.removeItem("token");
    localStorage.removeItem("user");

    return { success: true };
  } catch (error) {
    console.error("Logout error:", error);

    localStorage.removeItem("token");
    localStorage.removeItem("user");

    return { success: false, error };
  }
};
