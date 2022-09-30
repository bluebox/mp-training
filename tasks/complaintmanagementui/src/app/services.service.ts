import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http'
import { Facility, Complaint, Employee } from './Facility';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  private apiUrl = 'http://localhost:8000/';
  constructor(private http: HttpClient) { }
  postEmployee(data: any) {
    console.log(data);

    return this.http.post(this.apiUrl, data)
  }

  getLogindetails(id: number) {
    console.log(this.apiUrl);

    return this.http.get(this.apiUrl + "?id=" + id)
  }

  getEmployee(name: string): Observable<Employee> {
    return this.http.get<Employee>(this.apiUrl + "employee?name=" + name)
  }

  getComplaints() {
    let userdata = sessionStorage.getItem("userdetails")
    let user
    if (userdata) {
      user = JSON.parse(userdata)
    }
    return this.http.get<Complaint[]>(this.apiUrl + "complaint?id=" + user.emp_id)
  }


  getFacility(id: number): Observable<Facility> {
    return this.http.get<Facility>(this.apiUrl + "?id=" + (id));
    // let data = {
    //   "id":23,
    //   "location" :"hyd",
    //   "dept" :"cs"

    // } 
    // return data;
  }
  getComplaint(id: number) {
    return this.http.get(this.apiUrl + "device?id=" + id)
  }

  postComplaint(data: any) {
    console.log(this.apiUrl);
    //  let data=JSON.stringify(complaint)
    //  console.log(data);
    // let object=JSON.parse(data)

    return this.http.post(this.apiUrl + "complaint", data)
  }
  getTasks() {
    let userdata = sessionStorage.getItem("userdetails")
    let user
    if (userdata) {
      user = JSON.parse(userdata)
    }
    return this.http.get<any[]>(this.apiUrl + "tasks?id=" + user.emp_id)

  }

  getManagerTasks(id: number) {
    return this.http.get(this.apiUrl + "manager?id=" + id)
  }
  getEmployeelist(id :number){
    return this.http.get(this.apiUrl+"facility?id="+id)
  }

  setComplaint(comp_id:number,emp_id:string){
    return this.http.get(this.apiUrl+"assign?emp_id="+emp_id+"&comp_id="+comp_id)
  }

  editprofile(data :any ){
    let userdata = sessionStorage.getItem("userdetails")
    let user
    if (userdata) {
      user = JSON.parse(userdata)
    }
    return this.http.post(this.apiUrl+"edit?id="+user.emp_id,data)
  }
  employeesearch(name :string){
    return this.http.get(this.apiUrl+"searchemployee?name="+name)
  }

  getfacilities(){
    return this.http.get(this.apiUrl+"facilities")
  }
  updatestatus(comp_id :number){
    return this.http.get(this.apiUrl+"updatestatus?comp_id="+comp_id)
  }
  complaintItem(comp_id :number){
    return this.http.get(this.apiUrl+"complaintitem?comp_id="+comp_id)
  }
  getDeviceName(device_id:number){
    return this.http.get(this.apiUrl+"devicename+?device_id="+device_id)
  }


}
