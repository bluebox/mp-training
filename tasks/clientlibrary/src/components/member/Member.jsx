import React from "react";

const Member = ({ member }) => {
  const getGender = (gender) => {
    if (!gender) return "Other";
    if (gender.toLowerCase() === "m") return "Male";
    if (gender.toLowerCase() === "f") return "Female";
    return "Other";
  };

  return (
    <tr>
      <td className="px-4 py-3">{member.memberId}</td>
      <td className="px-4 py-3">{member.name}</td>
      <td className="px-4 py-3">{member.email}</td>
      <td className="px-4 py-3">{member.mobile}</td>
      <td className="px-4 py-3">{getGender(member.gender)}</td>
      <td className="px-4 py-3">{member.address}</td>
    </tr>
  );
};

export default Member;
