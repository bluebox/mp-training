import { Injectable } from '@angular/core';
import{HttpClient,HttpHeaders} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Customer, CustomerGroup, Login, Product, product_type } from './data';


@Injectable({
  providedIn: 'root'
})
export class DataserveService {

  constructor(private http:HttpClient) { }
   url="https://jsonplaceholder.typicode.com/posts/1";
    newurl="http://127.0.0.1:8000/";

   x:Customer[]=[
    {
      name:'bhargavi',
      mail: "x@GMAIL.COM",
      address:"jhj",
      ph: 676767,
      city: "sng",
      country: "india",
      pincode: 68
      }
   ]

  getDetails(){
    return this.http.get(this.url);
  }

newfun(){
  return this.x;
}

postCustLogin(data: any):Observable<Login>{
  const body = data;
  console.log(body);
  return this.http.post<any>('http://127.0.0.1:8000/user_login/' ,body)
}



postCustDetailsnew(data:CustomerGroup):Observable<CustomerGroup>{

  const headers = { "Content-Type": "application/json"};
  const body = data;
  return this.http.post<CustomerGroup>('http://127.0.0.1:8000/buyers/', body)
  // console.log(data);
  
  // return this.http.post(this.newurl,body,{ headers });

}
getCustDetailsnew():Observable<CustomerGroup>{

  
  return this.http.get<CustomerGroup>('http://127.0.0.1:8000/home/')
  // console.log(data);
  
  // return this.http.post(this.newurl,body,{ headers });

}

getProducts():Observable<Product> {

  return this.http.get<Product>('http://127.0.0.1:8000/products/');

}

getProductTypes():Observable<product_type> {
  console.log("jhnn")
  return this.http.get<any>(this.newurl+'product_type/');
}


// router-guard
isLoggedIn:boolean= false;
isAuthenticated(){
    return this.isLoggedIn;
  }


}
