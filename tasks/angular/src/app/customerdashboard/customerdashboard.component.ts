import { HttpClient } from '@angular/common/http';
import { ReadVarExpr } from '@angular/compiler';
import { AbstractType, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router"
import { UserserviceService } from '../services/userservice.service';

@Component({
  selector: 'app-customerdashboard',
  templateUrl: './customerdashboard.component.html',
  styleUrls: ['./customerdashboard.component.css']
})
export class CustomerdashboardComponent {
  signInCheck: any = true
  customerData: any;
  parsedData: any;
  accountData: any;
  parsedAccountData: any;
  trasactionData: any;
  customerProfile: any;
  token: any;
  obj: any;
  selectedFile: any;
  url: any;
  urlLink: any;
  constructor(private router: Router, private userdata: UserserviceService,
    private route: ActivatedRoute, private http: HttpClient) {

    if (localStorage.getItem("customer_refresh_token")) {

      this.token = this.getUserDetails()
      let userDetails;
      if (this.token) {
        userDetails = this.token.split(".")[1];
        userDetails = window.atob(userDetails);
        userDetails = JSON.parse(userDetails)
        this.obj = { "customer_email": userDetails.emp_id }

        this.http.post("api/user_details/", this.obj).subscribe((res) => {
          let response = JSON.stringify(res)
          let parsed = JSON.parse(response)
          this.customerProfile = parsed.user_data
          this.imageUrl =  parsed.user_img.profile_image
          console.log(this.imageUrl);

        })
      }
      else {
      }




      this.router.navigate(['/cusdashBoard'])
      this.userdata.setmsg(this.signInCheck)
      this.userdata.setmsg(this.signInCheck)





    }
    else {
      this.router.navigate(['/home'])
    }
  }

  ngOnInit(): void {

    this.userdata.loginUser.subscribe((response) => {
      console.log("yes");
      this.customerProfile = response
      // console.log(this.customerProfile);
    })


  }

  getUserDetails() {
    return localStorage.getItem("customer_refresh_token")


  }
  imageUrl: any

  imageFile(event: any) {
    this.selectedFile = <File>event.target.files[0]
    let formData = new FormData()
    formData.append('image', event.target.files[0])

    this.http.post('api/upload_image/', formData).subscribe((res: any)=>{
      console.log(res);
      this.imageUrl = res.imageUrl;
    })
  }

  imageUpload() {
  //   this.urlLink = this.url
  //   console.log(this.urlLink);
    this.token = this.getUserDetails()
        let userDetails;
        if (this.token) {
          userDetails = this.token.split(".")[1];
          userDetails = window.atob(userDetails);
          userDetails = JSON.parse(userDetails)
          this.obj = {"customer_email":userDetails.emp_id, "userLink":this.imageUrl }

          this.http.post("api/image_upload/", this.obj).subscribe((res)=>{
            console.log(res);
          })
        }


  }


}
