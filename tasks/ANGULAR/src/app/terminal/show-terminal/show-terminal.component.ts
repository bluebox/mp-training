import { Component, Input, OnInit } from '@angular/core';
import { OutletContext } from '@angular/router';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-show-terminal',
  templateUrl: './show-terminal.component.html',
  styleUrls: ['./show-terminal.component.css']
})
export class ShowTerminalComponent implements OnInit {

  constructor(private service:SharedService) { }
  @Input() ter:any;
 terminal_id:number|undefined;
 terminal_description:string="";
 airport_id:number|undefined;


  ngOnInit(): void {
    this.terminal_id=this.ter.terminal_id;
    this.terminal_description=this.ter.terminal_description;
    this.airport_id=this.ter.airport_id;

     }
  adddemo(){
  var val={terminal_id:this.terminal_id,
    terminal_description:this.terminal_description,
    airport_id:this.airport_id,
    };

    this.service.addTerminallist(val).subscribe(res=>{
      alert(res.toString());
    });
  }

    updatedemo(){
      var val={terminal_id:this.terminal_id,
        terminal_description:this.terminal_description,
        airport_id:this.airport_id,
        };

        this.service.updateTerminallist(val).subscribe(res=>{
          alert(res.toString());
        });

      }

    }

