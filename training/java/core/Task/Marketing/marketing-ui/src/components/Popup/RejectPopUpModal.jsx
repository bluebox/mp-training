import React, { useState } from "react";
import { Button, Modal } from "react-bootstrap";
import Validate from "../../helpers/Validate";

const RejectPopupModal = (props) => {

    const validate = Validate();
    const [comments, setComments] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!(comments && comments.length > 0 && comments.trim().length > 0)) {
            setErrorMessage("Reason is Mandatory.");
            return;
        }
        if (comments && comments.length > 255) {
            setErrorMessage("Maximum reason length must be 250 characters.");
            return;
        }
        props.reject(props.requestId, comments);
    }

    return (
        <React.Fragment>
            <Modal show={props.openModal} backdrop="static" onHide={() => props.setOpenModal(!props.openModal)} aria-labelledby="contained-modal-title-vcenter" centered>
                <Modal.Header className="p-12" closeButton={() => props.setOpenModal(!props.openModal)}>
                    <Modal.Title className="h6">
                        Reject Popup {props.ModalName}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className="row g-3 mb-4">
                        {validate.isNotEmpty(props.configurationName) && <div className="d-flex flex-column col-12"><p className="mb-0 font-12 text-secondary">Popup Configuration Name</p> <p className="mb-0 font-14">{props.configurationName}</p></div>}
                        {validate.isNotEmpty(props.requestId) && <div className="d-flex flex-column col-6"><p className="mb-0 font-12 text-secondary">Request ID</p> <p className="mb-0 font-14">{props.requestId}</p></div>}
                        {validate.isNotEmpty(props.templateName) && <div className="d-flex flex-column col-6"><p className="mb-0 font-12 text-secondary">Template Name</p> <p className="mb-0 font-14">{props.templateName}</p></div>}

                    </div>
                    <div>
                        <textarea maxlength="250" class="form-control" id={`reject-popup-${props.ModalName == "Template" ? "template" : "configuration"}`} rows="4" placeholder="Please specify reason for rejection" onChange={(e) => setComments(e.target.value)} onFocus={()=>setErrorMessage("")} />
                        {validate.isNotEmpty(errorMessage) && <div class="invalid-feedback d-block" > {errorMessage}</div>}
                        {validate.isEmpty(errorMessage) && <small className="text-end w-100 d-block text-muted font-12">{comments.length}/250</small>}
                    </div>
                </Modal.Body>
                <Modal.Footer className="p-2 justify-content-center flex-row-reverse">
                        <Button variant=" " className="px-4 me-3 btn-sm btn-brand" onClick={(e) => handleSubmit(e)}>Yes, Reject this {props.ModalName}</Button>
                        <Button  variant=" " className="px-4 me-3 brand-secondary btn-sm btn" onClick={() => props.setOpenModal(!props.openModal)}>Cancel</Button>
                </Modal.Footer>
            </Modal>
        </React.Fragment>
    )
}

export default RejectPopupModal;