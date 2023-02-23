
import React from "react";
import { useEffect } from "react";
function MRegister() {
    useEffect(()=>{
        document.title="Instructor Register";
    });
    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-6 offset-3">
                    <div className="card">
                        <h5 className="card-header">
                            Instructor Register
                        </h5>
                        <div className="card-body">
                            <form>
                                <div className="mb-3">
                                    <label for="firstNameI" className="form-label">First Name</label>
                                    <input type="text" className="form-control" id="firstNameI" aria-describedby="emailHelp" />
                                </div>
                                <div className="mb-3">
                                    <label for="lastNameI" className="form-label">Last Name</label>
                                    <input type="text" className="form-control" id="lastNameI" aria-describedby="emailHelp" />
                                </div>
                                <div className="mb-3">
                                    <label for="inputEmailI" className="form-label">Email Name</label>
                                    <input type="email" className="form-control" id="inputEmailI" aria-describedby="emailHelp" />
                                </div>
                                <div className="mb-3">
                                    <label for="inputPasswordI" className="form-label">Password</label>
                                    <input type="password" className="form-control" id="inputPasswordI" />
                                </div>
                                <div className="mb-3">
                                    <label for="qualificationI" className="form-label">Qualification</label>
                                    <input type="text" className="form-control" id="qualificationI" />
                                </div>
                                <div className="mb-3">
                                    <label for="desigI" className="form-label">Designation</label>
                                    <input type="text" className="form-control" id="desigI" />
                                </div>
                                <div className="mb-3">
                                    <label for="contactI" className="form-label">Contact Number</label>
                                    <input type="tel" className="form-control" id="contactI" placeholder="123 456 7890" pattern="[6-9][0-9][0-9] {4}{5}{6} [0-9][0-9][0-9]"/>
                                </div>
                                <div className="mb-3">
                                    <label for="exampleInputPassword1" className="form-label">Skills</label>
                                    <textarea className="form-control"></textarea>
                                    <div id="emailHelp" class="form-text">Html, Css , Javascript etc</div>
                        </div>
                        <div className="mb-3 form-check">
                            <input type="checkbox" className="form-check-input" id="exampleCheck1" />
                            <label className="form-check-label" for="exampleCheck1">Remember me</label>
                        </div>
                        <button type="submit" className="btn btn-primary">Sign Up</button>
                    </form>
                </div>
            </div>
        </div>
            </div >
        </div >
    )
}

export default MRegister;