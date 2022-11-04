import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-show-demo',
  templateUrl: './show-demo.component.html',
  styleUrls: ['./show-demo.component.css']
})
export class ShowDemoComponent implements OnInit {

  constructor(private service:SharedService) { }
  // modaltitle : string='';
  // // Activeaddcom:boolean=false;
  // // dem:any[];
 DemoList:any = [];
 modaltitle:string='';
 Activeaddcom:boolean=false;
//  flag:boolean=false;
 dem:any=[];
  ngOnInit(): void {
     this.refreshDemoList();


  }
 addclick(){
   this.dem={
    airport_id:0,
    airport_name:"",
    airport_owner:"",
    city:" ",

   };
  this.modaltitle="Add airport";
  this.Activeaddcom=true;
 }
 closeclick()
{
  this.Activeaddcom=false;
  this.refreshDemoList();
}

editclick(val:any)
{
   this.dem=val;
  this.Activeaddcom=true;
}
deleteclick(item:any)
{
  if(confirm('are you sure??'))
  {
  this.service.deletetDemolist(item.airport_id).subscribe(data=>{
     alert(data.toString());
     this.refreshDemoList();

    });

  }
}


  refreshDemoList(){
   this.service.getDemolist().subscribe(data=>{
    this.DemoList=data;});

   }


}
