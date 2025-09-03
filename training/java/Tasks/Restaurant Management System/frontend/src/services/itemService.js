import api from "../config/axiosConfig";

export const getItems = async () => {
  const res = await api.get("/api/items/all");
  return res.data.data || [];
};

export const deleteItem = async (id) => {
  const res = await api.delete(`/api/items/delete/${id}`);
  return res.data;
};

export const addItem = async (item) => {
  const res = await api.post("/api/items/addItem", item);
  return res.data;
};

export const uploadImage = async (file) => {
  try {
    const formData = new FormData();
    formData.append("file", file);

    const res = await api.post("/api/items/itemImage", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    return res.data.data;
  } catch (error) {
    console.error("Upload failed:", error);
    throw error;
  }
};

export const updateItem = async (id, item) => {
  const res = await api.post(`/api/items/updateItem/${id}`, item);
  return res.data;
};

export const getCategories = async () => {
  const res = await api.get(`/api/items/categories`);
  return res.data.data || [];
};

export const getSearchItems = async (search, category) => {
  const res = await api.get(`/api/items/searchItems`, {
    params: { search, category }, // <-- query params
  });
  return res.data.data || [];
};
