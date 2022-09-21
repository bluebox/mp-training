import { Component, OnInit } from '@angular/core';
import { Owner } from 'src/assets/products';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  owners: Owner[] = [];


  constructor(private auth: AuthService) { }

  ngOnInit(): void { }



  // getDetails() {
  //   this.auth.getOwners().subscribe(
  //     data =>
  //       this.owners = data
  //   )
  // }

  getDetails(): void {
    this.owners = this.auth.getOwners();
  }

  
}



// const locationsSubscription = locations.subscribe({
//   next(position) {
//     console.log('Current Position: ', position);
//   },
//   error(msg) {
//     console.log('Error Getting Location: ', msg);
//   }
// })