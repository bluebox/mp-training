import { useState,useEffect } from "react";
import {addExpense, fetchCategories} from "../../service/expenseService";

function AddSubmission() {
    
    const [categories,setCategories] = useState([])
    const [currDate,setCurrDate] = useState(new Date().getDate());
    

    const [formData, setFormData] = useState({
        categoryId: '',
        amount: 0,
        date:'',
        description:'',
        managerId:0,
        employeeId:0
    });
    

    useEffect(() => {
        const fetchData = async () => {
            try {
                const username = localStorage.getItem("username");
                const password = localStorage.getItem("password");
                const res = await fetchCategories(username, password);
                setCategories(res);
                
                
            } catch (error) {
                alert(error);
            }
        };
        console.log(currDate);
        fetchData();
    }, []);



    const handleChange = (e) =>{
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    }


    const handleSubmit= async (e)=>{
        e.preventDefault();
        addExpense(formData)
                        .then((res)=>alert(res.message))
                        .catch(e=>alert(e))
        
        setFormData({
            categoryId: '',
            amount: 0,
            description:'',
            receiptUrl:'',
            date:''
        })
    }


    
    const date = new Date();
    return (
    <div>
        <form onSubmit={handleSubmit} className="container mt-5" style={{ maxWidth: '400px' }}>
            <h2 className="mb-3">Add a new Expense</h2>
            <div className="mb-3">
                <label htmlFor="categoryId" className="form-label">Select Category</label>
                    <select
                    id="categoryId"
                    name="categoryId"
                    onChange={handleChange}
                    value={formData.categoryId}
                    required
                    className="form-select"
                    >
                <option value="" >Choose a Category</option>
                {categories.map(category => (
                    <option key={category.id} value={category.id}>
                    {category.name} - {category.monthly_limit}
                    </option>
                ))}
                </select>
            </div>

            <div className="mb-3">
                <label htmlFor="amount" className="form-label">Expense Amount</label>
                <input type="text" id="amount" name="amount" value={formData.amount} onChange={handleChange} className="form-control" />
            </div>

            <div className="mb-3">
                <label htmlFor="date" className="form-label">{formData.categoryId === "4" ? <span>Subscription Start Date , Must be this month</span> : <span>Date</span>}</label>
                {formData.categoryId === "4" ? 
                    <input 
                        type="date" 
                        name="date" 
                        id="date" 
                        value={formData.date} 
                        max={new Date(date.getFullYear(), date.getMonth(), 29).toISOString().split("T")[0]}
                        min={new Date(date.getFullYear(), date.getMonth(), 2).toISOString().split("T")[0]}
                        onChange={handleChange}
                        className="form-control"
                    />
                    :
                    <input 
                        type="date" 
                        name="date" 
                        id="date" 
                        value={formData.date} 
                        max={date.toISOString().split("T")[0]} 
                        onChange={handleChange} 
                        className="form-control"
                    />
                }
            </div>
            <div className="mb-3">
                <label htmlFor="description" className="form-label">Description</label>
                <input type="text" name="description" id="description" value={formData.description} onChange={handleChange} className="form-control" />
            </div>

            <div className="mb-3">
                <label htmlFor="receiptUrl" className="form-label">Reciept Url</label>
                <input type="text" name="receiptUrl" id="receiptUrl" value={formData.receiptUrl} onChange={handleChange} className="form-control" />
            </div>

            <button type="submit" className="btn btn-primary w-100">Submit</button>
        </form>
    
    </div>
    );
}

export default AddSubmission;