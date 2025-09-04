import { useEffect, useState } from "react";
import { fetchCategories } from "../../service/expenseService";
import { updateMySubmission } from "../../service/mysubmissionsService";

const EditMySubmission = ({currentSubmission,onClose}) =>{
    const [categories,setCategories] = useState([]);
    const [formData, setFormData] = useState({
            id:0,
            categoryId: '',
            amount: 0,
            description:'',
            managerId:0,
            employeeId:0
    });

    useEffect(() => {
            setFormData(currentSubmission);
            const fetchData = async () => {
                try {
                    const res = await fetchCategories();
                    setCategories(res);
                } catch (error) {
                    alert(error);
                }
            };
            fetchData();
    }, []);
    
    
    const handleChange = (e) =>{
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    }

    const handleSubmit = (e) =>{
        e.preventDefault();
        updateMySubmission(formData)
                            .then((res)=>{alert(res.message)})
                            .catch((e)=>alert(e.message))        
        onClose();
    }   
    
    return(
        <div>
            <form onSubmit={handleSubmit} className="container mt-5" style={{ maxWidth: '400px' }}>
            <h2 className="mb-3">Update Expense</h2>
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
                <label htmlFor="date" className="form-label">Date</label>
                <input type="date" name="date" id="date" value={formData.date} max={new Date().toISOString().split("T")[0]} onChange={handleChange} className="form-control"/>
            </div>

            <div className="mb-3">
                <label htmlFor="description" className="form-label">Description</label>
                <input type="text" name="description" id="description" value={formData.description} onChange={handleChange} className="form-control" />
            </div>
            <div className="mb-3">
                <label htmlFor="receiptUrl" className="form-label">Reciept Url</label>
                <input type="text" name="receiptUrl" id="receiptUrl" value={formData.receiptUrl} onChange={handleChange} className="form-control" />
            </div>
            <button type="submit" className="btn btn-primary mb-3 w-100">Update</button>
            <button onClick={onClose} className="btn btn-secondary mb-5 w-100">Close</button>
            </form>
        </div>
    )
}

export default EditMySubmission;