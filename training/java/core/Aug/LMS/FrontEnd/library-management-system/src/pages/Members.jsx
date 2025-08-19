import { useEffect, useState } from "react";
import Swal from "sweetalert2";
import {
  addMember,
  deleteMember,
  getMembers,
  updateMember,
} from "../services/memberService";
import TableTemplate from "../components/TableTemplate";
import MemberModal from "../components/MemberModel";

function Members() {
  const [members, setMembers] = useState([]);
  const [filteredMembers, setFilteredMembers] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [editingMember, setEditingMember] = useState(null);
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    mobile: "",
    gender: "",
    address: "",
  });
  const [searchTerm, setSearchTerm] = useState("");
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchMembers();
  }, []);

  useEffect(() => {
    if (searchTerm.trim() === "") {
      setFilteredMembers(members);
    } else {
      const lower = searchTerm.toLowerCase();
      setFilteredMembers(
        members.filter(
          (m) =>
            m.name.toLowerCase().includes(lower) ||
            String(m.mobile).includes(searchTerm)
        )
      );
    }
  }, [searchTerm, members]);

  const fetchMembers = async () => {
    try {
      const res = await getMembers();
      if (res.success) {
        setMembers(res.data);
      }
    } catch (err) {
      errorShow(err.response?.data?.message || "Failed to load members");
    }
  };

  const successShow = (message) => {
    Swal.fire({
      position: "top",
      icon: "success",
      text: message,
      timer: 1800,
      showConfirmButton: false,
      toast: true,
    });
  };

  const errorShow = (message) => {
    Swal.fire({
      position: "top",
      icon: "error",
      text: message,
      timer: 2000,
      showConfirmButton: false,
      toast: true,
    });
  };

  const openModal = (member = null) => {
    setEditingMember(member);
    setFormData(
      member
        ? member
        : { name: "", email: "", mobile: "", gender: "", address: "" }
    );
    setError(null);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setEditingMember(null);
    setError(null);
  };

  const handleChange = (data) =>
    setFormData({ ...formData, ...data });

  const handleSave = async (data) => {
    try {
       
      if (!data.name || !data.email || !data.mobile) {
        setError("Name, Email, and Mobile are required!");
        return;
      }
      if (!/^[0-9]{10}$/.test(data.mobile)) {
        setError("Mobile number must be exactly 10 digits!");
        return;
      }

      let res;
      if (editingMember) {
        res = await updateMember(data);
      } else {
        res = await addMember(data);
      }

      if (res.success) {
        successShow(res.message);
        fetchMembers();
        closeModal();
      } else {
        setError(res.message || "Something went wrong");
      }
    } catch (err) {
      setError(err.response?.data?.message || "Something went wrong");
    }
  };

  const handleDelete = async (id) => {
    Swal.fire({
      title: "Are you sure?",
      text: "This member will be permanently deleted!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#d33",
      cancelButtonColor: "#3085d6",
      confirmButtonText: "Yes, delete it!",
      cancelButtonText: "Cancel",
    }).then(async (result) => {
      if (result.isConfirmed) {
        try {
          const res = await deleteMember(id);
          if (res.success) {
            successShow(res.message);
          }
          fetchMembers();
        } catch (err) {
          errorShow(err.response?.data?.message || "Delete failed");
        }
      }
    });
  };

  return (
    <div className="p-3">
      <h2>👤 Members Management</h2>

      <div className="d-flex justify-content-between mb-3">
        <button className="btn btn-primary" onClick={() => openModal()}>
          ➕ Add Member
        </button>
        <input
          type="text"
          className="form-control w-50"
          placeholder="Search by name or mobile..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <TableTemplate
        columns={[
          { key: "id", label: "ID" },
          { key: "name", label: "Name" },
          { key: "email", label: "Email" },
          { key: "mobile", label: "Mobile" },
          { key: "gender", label: "Gender" },
          { key: "address", label: "Address" },
          { key: "status", label: "Status" },
        ]}
        data={filteredMembers}
        onEdit={openModal}
        onDelete={handleDelete}
      />

      <MemberModal
        show={showModal}
        onClose={closeModal}
        onSave={handleSave}
        formData={formData}
        handleChange={handleChange}
        editingMember={editingMember}
        error={error}
      />
    </div>
  );
}

export default Members;
