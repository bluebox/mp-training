import React from "react";
import {useFormik ,Formik,ErrorMessage} from "formik";




function OldPracice(){


    const formik = useFormik( {
        initialValues:{
            name:'',age:'',email:''
        },
        validate:(values)=>{
            const errors={}
            if(!values.name){errors.name = "name is required"}
            if(!values.age){errors.age = "age is required"}
            if(!values.email){errors.email = "email is required"}
            return errors;

        },

        onSubmit: (values) => {console.log("form fadata",values)
            
        }
    })
console.log("form errors",formik.errors)
console.log("visited  Fields",formik.touched)
return (
    <Formik>
    <form style={{margin:'200px',background:'lightgreen',padding:'20px'}} onSubmit={formik.handleSubmit}>
        <lable htmlFor='name' >Name:</lable>
        <input type='text' name='name' placeholder="name" onBlur={formik.handleBlur} onChange={formik.handleChange}/>
        {formik.errors.name ? <div style={{ color: "red" }}>{formik.errors.name}</div>:null }

        <lable htmlFor='email' >Email:</lable>
        <input type='text' name='email' placeholder="email" onChange={formik.handleChange}></input>
        {formik.errors.email ? <div style={{ color: "red" }}>{formik.errors.email}</div>:null }

        <lable htmlFor='age' >Age:</lable>
        <input type='numbrer' name='age' placeholder="age" onChange={formik.handleChange}></input>
        {formik.errors.age ? <div style={{ color: "red" }}>{formik.errors.age}</div>:null }

        <button type="submit" >SUBMIT</button>
    </form></Formik>



);


}export default OldPracice;