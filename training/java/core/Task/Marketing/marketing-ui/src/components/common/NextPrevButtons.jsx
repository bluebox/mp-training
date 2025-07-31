import React, { useEffect, useMemo, useRef, useState } from 'react'
import Validate from '../../helpers/Validate';
import { API_URL } from '../../services/ServiceConstants';
import { Button } from "react-bootstrap";
import InfoIcon from './InfoIcon';
import { PromotionStatus } from '../../constants/PromotionConstants';
const NextPrevBtn = (props) => {
  const [isInfoModelOpen, setInfoModalOpen] = useState(props.showNote);
  const inputEl = useRef();
  const hasNoteItems = props.noteItems?.length > 0
  
  const btnName = useMemo(() => {
    if(Validate().isNotEmpty(props)) {
      
      if(props?.actionPermissions.isApprover && props?.globalPromotionStatus===PromotionStatus.inActive) {
        return "Approve";
      }
      else if(props?.actionPermissions?.isEdit) {
        return "Update";
      } 
    }
    return "Submit";
  }, [props])
  
  return (
    <>
    <div className={`d-flex position-absolute bottom-0 p-12 w-100 border-top start-0 ${props.isNoteRequired && hasNoteItems ? 'justify-content-between' :'justify-content-end'}`} >
    {props.isNoteRequired && hasNoteItems && <div>
        <Button variant="link link-dark" ref={inputEl} className='btn-lg mb-0 font-14' onClick={() => { setInfoModalOpen(!isInfoModelOpen) }} >
          Note <InfoIcon aria-hidden="true" modalOpen={isInfoModelOpen} />
        </Button>
    </div>}
      <div className='d-flex'>
        {!props.hidePrevBtn &&
        <button className='btn shadow-sm brand-secondary me-3 rounded' onClick={props.handlePrevBtnClick}>Back To {props.PrevTab}</button>
        }
        {props.changeNxtBtn &&
          <div>
            {btnName && (<button className={`btn px-3  ${btnName === 'Update' ? 'btn-primary' : 'btn-brand' } shadow-sm rounded` } onClick={props.handleNextBtnClick} disabled={(props?.actionPermissions?.isCloser || props?.globalPromotionStatus === PromotionStatus.active)? !props.isToDateChanged : false}>
                        {btnName} </button>
            )}
          </div>
        }
        {!props.changeNxtBtn &&
        <button className='btn btn-brand' onClick= {props.handleNextBtnClick} >Proceed To {props.nextTab}</button> 
        }
    </div>
    </div>
    {isInfoModelOpen && hasNoteItems && <div   class="customdropdown-position position-absolute  text-secondary" >
                <h6 class="text-dark font-14">Note Information</h6>
                <ol>
                  {props.noteItems?.map((eachNote)=>{
                      return(
                        <li>{eachNote}</li>
                      )
                    })}
                </ol>
            </div>
            }
    </>
  )
}
export default NextPrevBtn