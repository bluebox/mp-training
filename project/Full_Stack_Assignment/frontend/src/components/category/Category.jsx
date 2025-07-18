import React from "react";

const Category = ({ category }) => {
  return (
    <tr>
      <td>{category.categoryId}</td>
      <td>{category.categoryName}</td>
      <td>{new Date(category.createdAtDate).toLocaleString()}</td>
      <td>{category.updatedAtDate ? new Date(category.updatedAtDate).toLocaleString() : "—"}</td>
    </tr>
  );
};

export default Category;
