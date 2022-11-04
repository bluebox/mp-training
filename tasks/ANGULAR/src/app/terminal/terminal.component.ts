import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-terminal',
  templateUrl: './terminal.component.html',
  styleUrls: ['./terminal.component.css']
})
export class TerminalComponent implements OnInit {
  terminalList:any=[]
  modaltitle:string='';
  Activeaddcom:boolean=false;
  flag:boolean=false;
  ter:any=[];
  constructor( private service :SharedService) {}

  ngOnInit(): void {
    this.refreshTerminalList()
  }

  addclick(){
    this.ter={
    terminal_id:0,
     terminal_description:"",
     airport_id:0

    };
   this.modaltitle="Add terminal";
   this.Activeaddcom=true;
  }
  closeclick()
 {
   this.Activeaddcom=false;
   this.refreshTerminalList();
 }

 editclick(val:any)
 {
  this.ter=val;
  this.Activeaddcom=true;
 }

 deleteclick(item:any)
 {
   if(confirm('are you sure??'))
   {
   this.service.deletetTerminallist(item.terminal_id).subscribe(data=>{
      alert(data.toString());
      this.refreshTerminalList();

     });

   }
 }
 
  refreshTerminalList(){
    this.service.getTerminallist().subscribe(data=>{
     this.terminalList=data;});

    }



}
