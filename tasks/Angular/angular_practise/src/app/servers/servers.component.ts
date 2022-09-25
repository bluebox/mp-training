import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.css']
})


export class ServersComponent implements OnInit {
  @Input()
  uname: string | undefined;
  names = ''
  btnIs =true
  @Output()
  notify:EventEmitter<string> = new EventEmitter<string> ();
  
  constructor() { 
    
   

  }
  

  ngOnInit(): void {
  }
  clear(){
    this.names =""
  }
  passdata(){
    this.notify.emit("this msg fro child")
  }

}
