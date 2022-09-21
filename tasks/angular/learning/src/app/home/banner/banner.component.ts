import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Owner } from 'src/assets/products';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {

  constructor(private auth: AuthService) { }

  @Input()
  bannerData!: Owner;

  ngOnInit(): void {
  }

  getowner(id: number) {
    this.auth.getOwnerDetails(String(id)).subscribe(owner => console.log(owner));

  }

}
