import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  valueSearched : string = '';
  constructor() { }

  ngOnInit(): void {
  }


  @Output()
  searchedChanged : EventEmitter <string> = new EventEmitter();

  @Input()
  onSearch(){
    this.searchedChanged.emit(this.valueSearched);
  }


}
