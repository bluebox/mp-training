import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 10000,
});

// @param {string} method - HTTP method (GET, POST, PUT, PATCH, DELETE).
// @param {string} urlTemplate - URL with optional path params (e.g., "/users/:id").
// @param {object} [pathVars={}] - Path variable substitutions. like { id: 123 }.
// @param {object} [queryParams={}] - Query parameters. like { q: "laptop", sort: "price_asc" };
// @param {object} [body={}] - Request body (for POST/PUT/PATCH).

export const makeRequest = async (
  method,
  urlTemplate,
  pathVars = {},
  queryParams = {},
  body = {}
) => {
  const resolvedUrl = Object.entries(pathVars).reduce(
    (url, [key, value]) => url.replace(`:${key}`, encodeURIComponent(value)),
    urlTemplate
  );

  const needsBody = ["POST", "PUT", "PATCH", "GET"].includes(
    method.toUpperCase()
  );

  try {
    const response = await axiosInstance.request({
      method,
      url: resolvedUrl,
      params: queryParams,
      data: needsBody ? body : undefined,
      withCredentials: true,
      headers: {
        "Content-Type": "application/json",
      },
    });

    return response.data;
  } catch (err) {
    console.error("API error:", err?.response?.data || err.message);
    throw err;
  }
};
