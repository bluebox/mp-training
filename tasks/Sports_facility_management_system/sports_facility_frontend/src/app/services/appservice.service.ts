import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AppserviceService {
  private Refreshtokenurl:string ='http://127.0.0.1:8000/check-refresh-token?refresh_token='
  is_authenticated:boolean =false;
  is_admin!:boolean;
  is_user:boolean = false;
  constructor( private http: HttpClient ) { 
    let token = localStorage.getItem('refresh_token');
    this.getUser(token)
    this.CheckRefreshToken(token).subscribe(
      (data) => {
        let res = JSON.stringify(data);
        let Parsed = JSON.parse(res);
        this.is_authenticated=true
        if (Parsed.is_admin) {
          this.is_admin = true;
          
        } else {
          this.is_user=true
        }

        
      },
      (err) => {
        this.is_authenticated=false
      }
    );
  }

  // async ngOnInit() {
  //   let user = await axios.get(this.Refreshtokenurl+token)
  // }

  async getUser(token:any) {
    let res = await axios.get(this.Refreshtokenurl+token)
    console.log(res.data);
    this.is_admin = res.data.is_admin
  }


  CheckRefreshToken(token:any) {
    
    return this.http.get(this.Refreshtokenurl+token)

    

  }
}
