import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthInterceptor } from 'src/app/interceptors/auth.interceptor';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
import { SubjectServiceService } from 'src/app/services/subject-service/subject-service.service';
import { Location } from '@angular/common'
@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  username !: string
  constructor(private http: HttpServiceService,
    private subjectService: SubjectServiceService,
    private location :Location,
    private router: Router) { }
  ngOnInit(): void {
    this.subjectService.usernameSubject.subscribe(data=>{
      this.username = data
    })
  }
  logoutUser() {
    this.http.logoutUser().subscribe(() => {
      AuthInterceptor.accessToken = ''
      this.subjectService.logoutService()
      this.router.navigate(['login'])
    })
  }
  goBack(){
    this.location.back()
  }
}
