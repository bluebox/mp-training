import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-bg-video-component',
  templateUrl: './bg-video-component.component.html',
  styleUrls: ['./bg-video-component.component.css']
})
export class BgVideoComponentComponent implements OnInit {

  constructor() { }

  @Input() videoUrl!: string

  ngOnInit(): void {
  }

}
