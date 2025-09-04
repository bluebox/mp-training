import React, { useState } from 'react'
import { addmember } from '../services/MemberServices'
import { Dialog, DialogContent, DialogTitle, DialogContentText } from "@mui/material"
const AddMember = () => {
    const [newmember, setNewMember] = useState({
        member_Name: "",
        email: "",
        mobile_No: "",
        gender: "",
        address: ""
    })
    const [flag, setFlag] = useState(false)
    const [memberResponse, setMemberResponse] = useState("")
    const [showError, setShowError] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const addMember = async () => {
        try {
            console.log(newmember)
            const response = await addmember(newmember);
            if (response.status === 200) {
                setMemberResponse(response.data)
                setFlag(true)
            }
        } catch (error) {
            setErrorMessage(error.message)
            setShowError(true)
        }
    }
    return (
        <div>
            <div className="add-member-container">
                <h1 className="add-member-title">Add Member</h1>
                <div style={{ maxHeight: '400px', overflowY: 'auto', overflowX: 'hidden' }}>
                    <form >
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="name">Name:</label>
                            <input type="text" id="name" name="MemberName" className="add-member-input" placeholder="Enter Member Name"
                                onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, member_Name: e.target.value }) }) }} required />
                        </div>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="email">Email:</label>
                            <input type="email" id="email" name="email" className="add-member-input" placeholder="Enter Email Address(abc@gmail.com)"
                                onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, email: e.target.value }) }) }} required />
                        </div>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="number">Mobile Number:</label>
                            <input type="text" id="number" pattern="\d{10}" name="Mobile Number" className="add-member-input" placeholder="Enter Ph-No (1234345467)"
                                onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, mobile_No: e.target.value }) }) }} required />
                        </div>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="gender">Gender:</label>
                            <select id="gender" name="gender" className="add-member-select"
                                onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, gender: e.target.value }) }) }} required>
                                <option value="" disabled selected>Select Gender</option>
                                <option value="M" >M</option>
                                <option value="F">F</option>
                            </select>
                        </div>
                        <div className="add-member-form-group">
                            <label className="add-member-label" htmlFor="address">Address:</label>
                            <textarea rows="3" id="address" name="address" className="add-member-textarea" onChange={(e) => { setNewMember((preVal) => { return ({ ...preVal, address: e.target.value }) }) }} required></textarea>
                        </div>
                        <input type="button" value="Add Member" className="add-member-submit" onClick={addMember}  />
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
                    })
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

export default AddMember