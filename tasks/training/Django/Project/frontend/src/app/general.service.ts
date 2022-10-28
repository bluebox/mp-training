import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Token } from '@angular/compiler';
import { map, Subject } from 'rxjs';

const baseUrl ='http://localhost:8000/';



@Injectable({
  providedIn: 'root'
})

export class GeneralService {


  constructor(private http: HttpClient) { }

  trip = new Subject()
  bill = new Subject()

  ownerLogin(data: any)
  {
    return this.http.post(baseUrl + 'owner-login/', data)
  }
  registerOwner(data : any){
    return this.http.post(baseUrl + 'owner/',data)
  }
  getOwner(id : any){
    return this.http.get(baseUrl + 'owner-vehicle/' + id)
  }


  getprofileDetails(){
    let customer = window.sessionStorage.getItem('token');

    let token = customer?.split(':')[1];

    let headers = new HttpHeaders().set('Authorization', 'Token ' + token?.substring(1, token.length));
    return this.http.get(baseUrl + 'customerdetails/',
      { 'headers': headers }
      );
  }

  getOwnerProfileDetails(){
    let owner : any = window.sessionStorage.getItem('owner_token');
    let tokenObj = JSON.parse(owner);
    console.log(tokenObj.access_token)
    let headers = new HttpHeaders().set('Authorization', 'Token ' + tokenObj.access_token);
    return this.http.get(baseUrl + 'ownerdetails/',
      { 'headers': headers }
      );
  }

  updateVehiclePrice(id : any, data : any){
    console.log(baseUrl + 'updateprice/' + id +'/');

    return this.http.put(baseUrl + 'updateprice/' + id +'/' , data)

  }


  customerLogin(data: any)
  {
    return this.http.post(baseUrl + 'customer-login/', data)
  }


  registerCustomer(data : any){
    return this.http.post(baseUrl + 'customer/',data)
  }


  updateCustomerProfile(data : any ){
    let customer = window.sessionStorage.getItem('token');
    let token = customer?.split(':')[1];
    console.log(token);

    let headers = new HttpHeaders().set('Authorization', 'Token ' + token?.substring(1, token.length));

    return this.http.put(baseUrl + 'customerdetails/', data, { 'headers': headers })
  }

  updateOwnerProfile(data : any ){
    let owner : any = window.sessionStorage.getItem('owner_token');
    let tokenObj = JSON.parse(owner)

    let headers = new HttpHeaders().set('Authorization', 'Token ' + tokenObj.access_token);

    return this.http.put(baseUrl + 'ownerdetails/', data, { 'headers': headers })
  }

  getCustomer(id : any){
    return this.http.get(baseUrl + 'customer/'+ id + '/')
  }




  addVehicle(data : any){

    let owner : any = window.sessionStorage.getItem('owner_token');
    let tokenObj = JSON.parse(owner);

    console.log(tokenObj.access_token)
    let headers = new HttpHeaders().set('Authorization', 'Token ' + tokenObj.access_token);
    return this.http.post(baseUrl + 'add-vehicle/',data,
      { 'headers': headers });
  }


  getVehicle(){
    return this.http.get(baseUrl + 'vehicle/')
  }


  getOwnerVehicle(){
    let owner : any = window.sessionStorage.getItem('owner_token');
    let tokenObj = JSON.parse(owner);

    console.log(tokenObj.access_token)
    let headers = new HttpHeaders().set('Authorization', 'Token ' + tokenObj.access_token);
    return this.http.get(baseUrl + 'owner-vehicle/',
      { 'headers': headers }
      );
  }



  deleteVehicle(id : any ){
    return this.http.delete(baseUrl + 'delete-vehicle/' + id )
  }


  bookVehicle(data : any){
    let customer = window.sessionStorage.getItem('token');
    let token = customer?.split(':')[1];
    console.log(token);
    let headers = new HttpHeaders().set('Authorization', 'Token ' + token?.substring(1, token.length));

  return this.http.post(baseUrl + 'book/', data, { 'headers': headers })
  }



  orderHistory(page : number){
    let customer = window.sessionStorage.getItem('token');
    let token = customer?.split(':')[1];
    console.log(token);
    let headers = new HttpHeaders().set('Authorization', 'Token ' + token?.substring(1, token.length));

    return this.http.get(baseUrl + 'trip?page='+page, { 'headers': headers })
  }

  recievedOrders(page : number){

    let owner : any = window.sessionStorage.getItem('owner_token');
    let tokenObj = JSON.parse(owner);

    let headers = new HttpHeaders().set('Authorization', 'Token ' + tokenObj.access_token);

    return this.http.get(baseUrl + 'recieved-orders?page='+page, { 'headers': headers })
  }

  generateBill(id : any){
    return this.http.post(baseUrl + 'generatebill/' + id, {})
  }

  getBill(id : any){
    return this.http.get(baseUrl + 'getbill/' + id, {}).pipe(
      map(res=>{
        this.bill.next(res)
        return res
      })
    )
  }

  customerReview(id : any, data : any){
    return this.http.put(baseUrl + 'customer-review/' + id +'/', data )
  }

  ownerReview(id : any, data : any){

    return this.http.put(baseUrl + 'owner-review/' + id +'/', data )
  }

  getTrip(id: any){
    return this.http.get(baseUrl + 'trip/' + id + '/').pipe(
      map(resp=>{
        this.trip.next(resp)
        return resp
      })
    )
  }

  cancelOrder(id: any ){
    return this.http.put(baseUrl + 'cancel-order/' + id + '/', {})
  }
  logOutCustomer(){
    return this.http.post(baseUrl + 'logout-customer/', {})
  }


  logOutOwner(){
    return this.http.post(baseUrl + 'logout-owner/',{})
  }

  searchVehicle(key : any){
    return this.http.get(baseUrl + 'search-vehicle/?model=' + key)
  }

  addOdoReading(id : any, data: any){

    return this.http.put(baseUrl + 'add-odoreading/' + id + '/', data)
  }
  getBillList(){
    return this.http.get(baseUrl + 'bill-list/')
  }

  changeStatus(id : any){
    return this.http.put(baseUrl + 'vehicle-status/' + id + '/', {})
  }
  // addRating(id : any, data: any){
  //   return this.http.put
  // }

  getVehicleDetails(id : any){
    return this.http.get(baseUrl + 'get-vehicle-details/' + id)
  }

  getOwnerDetails(id:any){
    return this.http.get(baseUrl + 'get-owner-details/' + id)
  }

}


