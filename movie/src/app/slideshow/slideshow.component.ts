import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-slideshow',
  template: `
  <mat-tab-group mat-align-tabs="center" headerPosition="below">
  <mat-tab label="-"style="mergin-left:20vh"> <img src="/assets/data/dowload.png" style="height:50vh;width:99vw"> </mat-tab>
  <mat-tab label="-"> <img src="/assets/data/download (3).jpeg" style="height:50vh;width:99vw"> </mat-tab>
  <mat-tab label="-"><img src="/assets/data/download (2).jpeg" style="height:50vh;width:99vw"> </mat-tab>
</mat-tab-group>

  `,
  styles: [
  ]
})
export class SlideshowComponent implements OnInit {
  slideshow:string[]=["/assets/data/download (1).jpeg","/assets/data/download.jpeg","/assets/data/logo.png"]

  constructor() { }

  ngOnInit(): void {
  }

}
