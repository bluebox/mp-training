import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hollywood',
  templateUrl: './hollywood.component.html',
  styleUrls: ['./hollywood.component.css']
})
export class HollywoodComponent implements OnInit {
  public hmovies:string[]=["h1","h2","h3","h4"]
  longText = `The Shiba Inu is the smallest of the six original and distinct spitz breeds of dog
  from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was
  originally bred for hunting.`;

  constructor() { }

  ngOnInit(): void {
  }

}
