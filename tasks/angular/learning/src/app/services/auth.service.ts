import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Owner } from 'src/assets/products';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  owners: Owner[] = [];

  constructor(private http: HttpClient) { }

  apiUrl = "http://10.129.245.173:7000/"

  // getOwners(){
  //   return this.http.get<Owner[]>(this.apiUrl + 'owners');
  // }

  getOwners() {
    this.http.get<Owner[]>(this.apiUrl + 'owners').subscribe(data => this.owners = data
    );
    return this.owners;
  }

  getOwnerDetails(id: string) {
    return this.http.get<Owner>(this.apiUrl + 'owners/' + id);

  }
}







// private fetchData(){
//   const promise = this.httpClient.get(this.apiUrl).toPromise();
//   console.log(promise);  
//   promise.then((data)=>{
//     console.log("Promise resolved with: " + JSON.stringify(data));
//   }).catch((error)=>{
//     console.log("Promise rejected with " + JSON.stringify(error));
//   });