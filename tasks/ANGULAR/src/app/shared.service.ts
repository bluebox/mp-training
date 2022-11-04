import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  readonly APIUrl = "http://127.0.0.1:8000"
  readonly PhotoUrl = "http://127.0.0.1:8000/media"


  constructor(private http:HttpClient) {
    //
    // if (tokenlet token = this.getCookie('refresh_token')
    // console.log('no token'); && token != "") {
      // console.log(token);
      // console.log(this.getDecodedAccessToken(token));
      // this.isAuthenticated = true
      // this.isLogin.next(true)
    }
    // this.isLogin.next(true);



  getDemolist():Observable<any[]>{


      return this.http.get<any[]>(this.APIUrl + '/demo/');

  }

  addDemolist(val:any){

    return this.http.post(this.APIUrl + '/demo/',val);

  }

  updateDemolist(val:any){

    return this.http.put(this.APIUrl + '/demo/', val);

  }

  deletetDemolist(val:any){

    return this.http.delete(this.APIUrl + '/demo/'+val);

  }

  // UploadPhoto(val:any){

  //   return this.http.post(this.APIUrl+'/savefile', val);
  // }

  getAllDemo():Observable<any[]>{
    return this.http.get<any[]>(this.APIUrl+'/demo/');
  }

  getUsers(){
    return this.http.get(this.APIUrl+'/login/');
  }

  getTerminallist():Observable<any[]>{


    return this.http.get<any[]>(this.APIUrl + '/terminal/');

}
addTerminallist(val:any){

  return this.http.post(this.APIUrl + '/terminal/',val);

}

updateTerminallist(val:any){

  return this.http.put(this.APIUrl + '/terminal/', val);

}

deletetTerminallist(val:any){

  return this.http.delete(this.APIUrl + '/terminal/'+val);

}


getPassengerlist():Observable<any[]>{


  return this.http.get<any[]>(this.APIUrl + '/passenger/');

}
addPassengerlist(val:any){

  return this.http.post(this.APIUrl + '/passenger/',val);

}

updatePassengerlist(val:any){

  return this.http.put(this.APIUrl + '/passenger/', val);

}

deletetPassengerlist(val:any){

  return this.http.delete(this.APIUrl + '/passenger/'+val);

}
getAirlineslist():Observable<any[]>{


  return this.http.get<any[]>(this.APIUrl + '/airlines/');

}
addAirlineslist(val:any){

  return this.http.post(this.APIUrl + '/airlines/',val);

}

updateAirlineslist(val:any){

  return this.http.put(this.APIUrl + '/airlines/', val);

}

deletetAirlineslist(val:any){

  return this.http.delete(this.APIUrl + '/airlines/'+val);

}

obtainFlightlist(val:any)
{
  return this.http.post(this.APIUrl + '/flight/',val);
}

addTicket(val:any)
{
  return this.http.post(this.APIUrl + '/ticket/',val,{withCredentials:true});
}


loginpassenger(val:any)
{
  return this.http.post(this.APIUrl + '/login/',val, {withCredentials:true});
}

getschedule(val:any)
{
  return this.http.post(this.APIUrl + '/schedule/',val,{withCredentials:true});
}

getstafflist():Observable<any[]>{


  return this.http.get<any[]>(this.APIUrl + '/staff/');

}
addstaff(val:any)
{
  return this.http.post(this.APIUrl + '/staff/',val);
}
updatestaff(val:any){

  return this.http.put(this.APIUrl + '/staff/', val);

}

deletetstaff(val:any){

  return this.http.delete(this.APIUrl + '/staff/'+val);

}
getstaff_shifts_list():Observable<any[]>{
  return this.http.get<any[]>(this.APIUrl + '/staff-shifts/');
}
getluggage_list():Observable<any[]>{
  return this.http.get<any[]>(this.APIUrl + '/luggage/');
}


logout()
{
  return this.http.post(this.APIUrl +  '/logout/',{withCredentials: true});
}

getTicket():Observable<any[]>
{
  return this.http.get<any[]>(this.APIUrl + '/ticket/');
}

getTicketdetails():Observable<any[]>
{
  return this.http.get<any[]>(this.APIUrl + '/ticket-details/');
}
getTicketalldetails():Observable<any[]>
{
  return this.http.get<any[]>(this.APIUrl + '/ticket_all_details/');
}
uploadphoto(val:any)
{
  return this.http.post(this.APIUrl + '/upload/',val);
}

// postFile(caption: string, fileToUpload: File){
//   const endpoint = 'http://localhost:3343/api/UploadImage';
//   const formData: FormData = new FormData();
//   formData.append("Image", fileToUpload, fileToUpload.name);
//   formData.append("Caption", caption);
//   return this.http.post(endpoint,formData);
// }
isloggedin()
{
    return localStorage.getItem("isLoggedIn")
}
getbookings(val:any)
{
  return this.http.post(this.APIUrl + '/my-bookings/',val);
}
addluggage(val:any)
{
  return this.http.post(this.APIUrl + '/luggage/',val);
}
obtainfullFlightlist():Observable<any[]>
{
  return this.http.get<any[]>(this.APIUrl + '/flight/');
}
uploadprofile(val:any)
{
  return this.http.post(this.APIUrl + '/profile-img/',val);
}
mydetails(val:any)
{
  return this.http.post(this.APIUrl + '/my-details/',val);
}

editprofile(val:any)
{
  return this.http.put(this.APIUrl + '/passenger/', val);

}

}
