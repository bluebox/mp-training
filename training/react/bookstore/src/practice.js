import React ,{useState} from "react";
import {useFormik ,Formik,ErrorMessage} from "formik";

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';



function Pracice(){


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

  const notify = () => {
toast("Hello, this is a toast!");
toast.success("Success message!");
toast.error("Something went wrong!");
toast.info("Just some info.");
toast.warn("Be careful!");

  }
return (<>
	<form style={{margin:'200px',background:'lightgreen',padding:'20px'}} onSubmit={formik.handleSubmit}>
		<label htmlFor='name' >Name:</label>
		<input type='text' name='name' placeholder="name" {...formik.getFieldProps("name")}/>
		{formik.errors.name ? <div style={{ color: "red" }}>{formik.errors.name}</div>:null }

		<label htmlFor='email' >Email:</label>
		<input type='text' name='email' placeholder="email" {...formik.getFieldProps("email")}></input>
		{formik.errors.email ? <div style={{ color: "red" }}>{formik.errors.email}</div>:null }

		<label htmlFor='age' >Age:</label>
		<input type='number' name='age' placeholder="age" {...formik.getFieldProps("age")}></input>
		{formik.errors.age ? <div style={{ color: "red" }}>{formik.errors.age}</div>:null }

		<button type="submit" >SUBMIT</button>

	</form>

	 <div>
      <button onClick={notify}>Show Toast</button>
      <ToastContainer />
    </div>

	 <div>
      <button onClick={notify}>Show warn</button>
      <ToastContainer position="top-center" />
    </div>

		</>



);


}export default Pracice;