import React from 'react';
import { Form  } from 'react-bootstrap';
import CloseButton from 'react-bootstrap/CloseButton';

const InputWithOutFlotingPoint =(props) => {
    return (
        <React.Fragment>
            <Form.Control 
                        type={props.type} 
                        placeholder={props.placeholder} 
                        size={props.size}
                        aria-label={`${props.type} input`}
                        readOnly={props.readOnly}
                        disabled={props.disabled}
                        plaintext={props.plaintext}
                        value ={props.value} 
                        className = {props.controlClassName}
                        onChange={props.onChange}/>
                    {props.errorMsg !== undefined && <Form.Control.Feedback type="invalid">
                        {props.errorMsg}
                    </Form.Control.Feedback>}
                    {props.value && <CloseButton className='custom-close-icon' onClick={props.clearSearchText}/>}
                    
        </React.Fragment>
    )
}
export default InputWithOutFlotingPoint