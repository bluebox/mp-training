import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnChanges{
  title = 'ToruismClient';
  constructor(private router: Router, private auth: AuthService) {
    // this.auth.isLogin.subscribe(data=>console.log(data))
    // this.router.events
    //   .forEach(e => e instanceof NavigationEnd)
    //   .then((data)=> console.log(data))
  }

  ngOnChanges(changes: SimpleChanges): void {   // if @Input values changed then this method will be called
    console.log(changes);
    for (let propName in changes) {
      console.log(propName);
      console.log(changes[propName].currentValue);
      console.log(changes[propName].previousValue);
    }
  }

  // ngDoCheck() {
  //   console.log('do check');
  // }
}
