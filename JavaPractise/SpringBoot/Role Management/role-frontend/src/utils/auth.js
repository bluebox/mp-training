
export function getAuthHeader() {
  const username = localStorage.getItem("username");
  const password = localStorage.getItem("password");

  if (!username || !password) return {};

  const token = btoa(`${username}:${password}`);
  return { Authorization: `Basic ${token}` };
}

export function isLoggedIn() {
  return Boolean(localStorage.getItem("username") && localStorage.getItem("password"));
}

export function isAdmin() {
  return localStorage.getItem("role") === "ADMIN";
}
