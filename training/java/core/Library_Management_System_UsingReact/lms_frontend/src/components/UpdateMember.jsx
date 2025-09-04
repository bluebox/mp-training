import React, { useState, useEffect } from 'react'
import { getMemberById, updatemember } from '../services/MemberServices'
import { useParams , useNavigate} from 'react-router-dom';
import { Dialog, DialogContent, DialogTitle, DialogContentText } from "@mui/material"
const UpdateMember = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [newmember, setNewMember] = useState({
        member_Id: "",
        member_Name: "",
        email: "",
        mobile_No: "",
        gender: "",
        address: ""
    })

    useEffect(() => {
        if (id) {
            getMemberById(id)
                .then((response) => {
                    setNewMember({
                        member_Id: id,
                        member_Name: response.data.member_Name,
                        email: response.data.email,
                        mobile_No: response.data.mobile_No,
                        gender: response.data.gender,
                        address: response.data.address

                    });
                })
                .catch(error => {
                    console.error("Error fetching member details:", error);
                    setErrorMessage("Failed to load member details. Please try again.");
                    setShowError(true);
                });
        }
    }, [id]);

    const [flag, setFlag] = useState(false)
    const [memberResponse, setMemberResponse] = useState("")
    const [showError, setShowError] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const handleUpdate = (e) => {
        e.preventDefault();
        console.log(newmember)
        // setNewMember((preVal) => { return ({ ...preVal, member_Id: id }) })
        console.log(newmember) 
        updatemember(newmember)
            .then(response => {
                console.log(response.data);
                setMemberResponse(response.data);
                setFlag(true);
            })
            .catch(error => {
                console.error("Error in updating member:", error);
                setErrorMessage("Failed to update member. Please try again.");
                setShowError(true);
            });
    };
    return (
        <div>
            <div className="add-member-container">
                <h1 className="add-member-title">Update Member Details</h1>
                <div style={{ maxHeight: '400px', overflowY: 'auto', overflowX: 'hidden' }}>
                    <form onSubmit={handleUpdate}>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="name">Name:</label>
                            <input type="text" id="name" name="MemberName" className="add-member-input" placeholder="Enter Member Name" value={newmember.member_Name}
                                onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, member_Name: e.target.value }) }) }} required />
                        </div>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="email">Email:</label>
                            <input type="email" id="email" name="email" className="add-member-input" placeholder="Enter Email Address(abc@gmail.com)" value={newmember.email}
                                onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, email: e.target.value }) }) }} required />
                        </div>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="number">Mobile Number:</label>
                            <input type="text" id="number" pattern="\d{10}" name="Mobile Number" className="add-member-input" placeholder="Enter Ph-No (1234345467)" value={newmember.mobile_No}
                                onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, mobile_No: e.target.value }) }) }} required />
                        </div>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="gender">Gender:</label>
                            <select id="gender" name="gender" className="add-member-select" value={newmember.gender}
                                onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, gender: e.target.value }) }) }} required>
                                <option value="" disabled selected>Select Gender</option>
                                <option value="M" >M</option>
                                <option value="F">F</option>
                            </select>
                        </div>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="address">Address:</label>
                            <textarea rows="3" id="address" name="address" className="add-member-textarea" onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, address: e.target.value }) }) }} value={newmember.address} required></textarea>
                        </div>
                        <input type="submit" value="Update Member" className="add-member-submit" />
                        {/* <input type="submit" onclick="cancelForm(event)" name="action" value="Cancel" class="add-member-submit" style="background-color:#7f8c8d;"/> */}
                    </form>
                </div>
            </div>
            {flag &&
                <Dialog open={flag} onClose={() => {
                    setFlag(false), setNewMember({
                        member_Name: "",
                        email: "",
                        mobile_No: "",
                        gender: "",
                        address: ""
                    }), navigate(`/members`)
                }}>
                    <DialogTitle>
                        {"Success"}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>{memberResponse}</DialogContentText>
                    </DialogContent>
                </Dialog>
            }
            {
                showError &&
                <Dialog open={showError} onClose={() => setShowError(false)}>
                    <DialogTitle>
                        {"Error while uploading member details"}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>{errorMessage}</DialogContentText>
                    </DialogContent>
                </Dialog>
            }
        </div>
    )
}

export default UpdateMember