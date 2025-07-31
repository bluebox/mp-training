import React, { useState } from 'react';
import { Modal, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { withFormHoc } from "@medplus/react-common-components/DynamicForm";

const ConfirmSubmitModal = ({ show, handleClose, handleConfirm }) => {
  return (
    <>
    <div className='custom-border-bottom-dashed'>
    <Modal show={show} onHide={handleClose} backdrop={"static"} size="lg" aria-labelledby="contained-modal-title-vcenter" centered>
      <Modal.Header closeButton>
        <Modal.Title  className='h6'>Confirm Submission</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        Are you sure you want to continue without any complimentary references
      </Modal.Body>
      <Modal.Footer className="p-2 justify-content-center">
        <div>           
            <button type="button" tabindex="3" class="px-5 me-3 brand-secondary btn-sm btn" onClick={handleClose}>Cancel</button>
            <Button variant="brand" className="px-5 me-3  btn-sm btn" onClick={handleConfirm}>Yes, Continue without references</Button>
        </div>
      </Modal.Footer>
    </Modal>
    </div>
    </>
  );
};

export default withFormHoc(ConfirmSubmitModal);
