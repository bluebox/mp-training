export const loginUser = async (username, password) => {
  const response = await fetch('http://localhost:8080/login', {
    method: "POST",
    withCredntials: true,
    credentials:"include",
    headers: {
      "Authorization": "Basic " + btoa(`${username}:${password}`),
      "Content-Type": "application/json"
    }
  });
  console.log(response);
  if (!response.ok) {
    throw new Error('Login failed');
  }

  return await response.json();
};




export const logout = async () => {
  const response = await fetch('http://localhost:8080/logout', {
    method: "GET",
    withCredntials: true,
     credentials:"include",
    headers: {
      "Content-Type": "application/json"
    }
  });
  if (!response.ok) {
    throw new Error('Could not fetch user!');
  }

  return "wow what a sudden suppai";
};