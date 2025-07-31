import React, { useEffect, useState } from 'react'
import ReactDatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import Validate from '../../helpers/Validate';

const validate = Validate();
const CustomDateTimePicker = (props) => {
    const [startDate, setStartDate] =  useState(validate.isNotEmpty(props.defaultDateTime) ? props.defaultDateTime : new Date());
    const title = validate.isNotEmpty(props.title) ? props.title :'Date';
    const note = validate.isNotEmpty(props.note) ? props.note : undefined;
    const [defaultTime, setDefaultTime] = useState(validate.isNotEmpty(props.defaultTime) ? new Date(props.defaultTime) : new Date(new Date().getTime()+300000));
    const handleDateChange =(date) => {
        setStartDate(date)
        setDefaultTime(date);
        props.handleDateChange(date);
    } 

    const onDateTimeSelect = (date) => {
      const defaultDateTime = new Date(date);
      defaultDateTime.setHours(defaultTime.getHours(), defaultTime.getMinutes(), defaultTime.getSeconds());
      setStartDate(defaultDateTime)
      setDefaultTime(defaultDateTime)
      props.handleDateChange(defaultDateTime);
    }
    useEffect(() => {
      setStartDate(props.defaultDateTime)
    },[props.defaultDateTime]) 

    useEffect(() => {
      if(props.defaultTime){
        setDefaultTime(new Date(props.defaultTime))
      }
    },[props.defaultTime]) 
  return (  
    <React.Fragment>
        <div className='col-4 z-2' {...props} >
          <span className='text-muted small'>{title} <span className='ml-2 float-end'>{note}</span></span>
          <div className='custom-model-filter-container'>
          <ReactDatePicker className={props?.pickerProps?.readOnly ? `rounded w-100 p-12 form-control disabled-background`: `rounded w-100 p-12 form-control`}
              selected={startDate}
              onChange={(date) => handleDateChange(date)}
              showMonthDropdown
              showYearDropdown
              dropdownMode='select'
              timeInputLabel="Time:"
              dateFormat="MMMM dd yyyy h:mm aa"
              onSelect={(select) => onDateTimeSelect(select)}
              // showTimeInput 
              // onChangeRaw={(select) => onDateTimeSelect(select)}
              // showTimeSelectOnly
              showTimeSelect              
              timeIntervals={1}
              readOnly={props?.pickerProps?.readOnly}
              // minDate={props.minDate}
              maxDate={props.maxDate}
              filterDate={(date) => {
                const today = new Date(date.setHours(0, 0, 0, 0));
                const minDate = new Date(props.minDate);
                minDate.setHours(0, 0, 0, 0); 
                return today >= minDate; 
              }}
              // excludeDateIntervals={[
              //   { start: subDays(new Date(), 355), end: addDays(new Date(), 0) },
              // ]}
              placeholderText="DD/MM/YYYY HH:MM"
              popperPlacement="top-end"
              isClearable={Validate().isNotEmpty(props?.isClearable) ? props.isClearable : false}
          />
          </div>
        </div>

    </React.Fragment>
   
  )
}

export default CustomDateTimePicker