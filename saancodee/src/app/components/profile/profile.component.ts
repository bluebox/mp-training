import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username: null | string = localStorage.getItem('username')

  profile!:any;
  problems!: any;
  easyCent: any;
  hardCent: any;
  mediumCent: any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private service: RegisterService) { 
    this.service.getProfile().subscribe((data) => {
      console.log('====================================');
      console.log(data);
      this.profile = data
      this.service.profile = data;
      console.log('====================================');
    })
    this.problems = this.service.getStats(this.username).subscribe((data: any) => {
      this.problems = data;
      this.easyCent = (data['easy'] / data['totalEasy']) * 100;
      this.mediumCent = (data['medium'] / data['totalMedium']) * 100;
      this.hardCent = (data['hard'] / data['totalHard']) * 100;
    })
    // this.username = this.route.snapshot.params['username']
    // console.log(localStorage.getItem('token'))
    // const headers = new HttpHeaders(
    //   {
    //     'Content-Type': 'application/json',
    //     'Autherization': 'token ' + localStorage.getItem('token')
    //   }
    // )
    // this.http.get("http://127.0.0.1:8000/api/profile/" + this.username, {headers: headers}).subscribe((data) => {
    //   console.log(data)
    // })

    // this.service.getProfile().subscribe((data) => {
    //   console.log('====================================');
    //   console.log(data);
    //   console.log('====================================');
    // })
    
    // loadProfile() {
    //   const headers = new HttpHeaders(
    //     {
    //       'Content-Type': 'application/json',
    //       'Autherization': 'token ' + String(localStorage.getItem('currentUser'));
    //     }
    //   )
    //   this.http.get("http://127.0.0.1:8000/api/profile/" + this.username, {headers: headers})
    // }
  }


  ngOnInit(): void {
  }

}
