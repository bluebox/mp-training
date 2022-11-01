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
    return this.http.get(this.apiUrl + "?id=" + id)
  }
  getEmployee(name: string) {
    return this.http.get(this.apiUrl + "employee?name=" + name)
  }

  getComplaints(text:string,iscompleted:string) {
    let userdata = sessionStorage.getItem("userdetails")
    let user
    if (userdata) {
      user = JSON.parse(userdata)
    }
    return this.http.get<Complaint[]>(this.apiUrl + "complaint?id=" + user.emp_id+"&&text="+text+"&&isompleted="+iscompleted)
  }


  getFacility(id: number): Observable<Facility> {
    return this.http.get<Facility>(this.apiUrl + "?id=" + (id));
  }
  getComplaint(id: number) {
    return this.http.get(this.apiUrl + "device?id=" + id)
  }

  postComplaint(data: any) {
    console.log(data);
    return this.http.post(this.apiUrl + "complaint", data)
  }

  getTasks() {
    let userdata = sessionStorage.getItem("userdetails")
    let user
    if (userdata) {
      user = JSON.parse(userdata)
    }
    return this.http.get(this.apiUrl + "tasks?id=" + user.emp_id)

  }

  getManagerTasks(id: number,text:string,page:number,assignedto:any,isassigned:any) {
    return this.http.get(this.apiUrl + "manager?text=" + text+"&&id="+id+"&&page="+page+"&&assignedto="+assignedto+"&&isassigned="+isassigned)
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

  updatestatus(comp_id :number,message : string ){
    return this.http.get(this.apiUrl+"updatestatus?comp_id="+comp_id+"&message="+message)
  }


 createmployee(data : any){
  return this.http.post(this.apiUrl+"createemployee",data)
 }
 getassignedto(comp_id :number){
  return this.http.get(this.apiUrl+"getassignedto?comp_id="+comp_id)
 }
 getEmployeeDevices(id:number,page:number){
  return this.http.get(this.apiUrl+"device?id="+id+"&&page="+page)
 }

 detetecomplaint(comp_id:number){ 
  return this.http.delete(this.apiUrl+"deletecomplaint?comp_id="+comp_id)
 }
 adddevice(data : any){
  return this.http.post(this.apiUrl+"device",data)
 }
 deleteaccount(id : number){
  return this.http.delete(this.apiUrl+"createemployee?emp_id="+id)
 }
 postfacility(data : any ){
  return this.http.post(this.apiUrl+"facility/",data)
 }
 getComplaintsserarch(text:string,iscompleted:string){
  let userdata = sessionStorage.getItem("userdetails")
  let user
  if (userdata) {
    user = JSON.parse(userdata)
  }
  return this.http.get(this.apiUrl+"complaintsearch?text="+text+"&&id="+user.emp_id+"&&iscompleted="+iscompleted)
 }
 getComplaintSearch(id:number,text:string,page:number,iscompleted:string){
  return this.http.get(this.apiUrl+"getcomplaiants?text="+text+"&&id="+id+"&&page="+page+"&&iscompleted="+iscompleted)

 }
 deletedevice(id:number){
  return this.http.delete(this.apiUrl+"device?id="+id)
 }
 getProfile(emp_id:number){
  return this.http.get(this.apiUrl+"profile?emp_id="+emp_id)
 }

}
