export interface Facility{
    facility_id :number;
    facility_location :string;
    facility_dept : string ;

} 
export interface Complaint {
    facility_id: number;    
    emp_id :number;
    device_id: number;
    comp_desc : string;
}
export interface Employee{
    emp_id:number;
    emp_name:string;
    emp_mobile:string;
    emp_email:string;
    emp_role:string;
    emp_pic:ImageBitmap;
    facility_id:number;
   
}

// class EMployeeClass implements Employee{

//     getName (){
//         return this.
//     }
// }