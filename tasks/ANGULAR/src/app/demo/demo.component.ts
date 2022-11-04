
import { fruit } from '../fruit';
import { Fruits } from '../fruitlist';
import { Component, EventEmitter, Input, OnInit,Output } from '@angular/core';


@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})

export class DemoComponent implements OnInit {

  @Output() newEvent = new EventEmitter<string>();

  @Input() data='';

  exfr = Fruits;

  selfruit : fruit | undefined;

  constructor() { }

  ngOnInit(): void {
  }

  onSelect(fr:fruit): void
  {
    this.selfruit=fr;
  }

  addnewItem(item:string)
  {
    this.newEvent.emit(item);
  }


}
