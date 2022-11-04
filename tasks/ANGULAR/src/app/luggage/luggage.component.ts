import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { elementAt } from 'rxjs';
import { SharedService } from 'src/app/shared.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-luggage',
  templateUrl: './luggage.component.html',
  styleUrls: ['./luggage.component.css']
})
export class LuggageComponent implements OnInit {
luggageForm:any;
airport_name: any;

  constructor(private service :SharedService, private router:Router) { }
  luggageList:any;
  ngOnInit(): void {

    this.luggageForm = new FormGroup({
      luggage_type: new FormControl(''),
      luggage_weight: new FormControl('', Validators.min(3)),

    });
    this.refreshLuggageList()
  }

refreshLuggageList()
{
  this.service.getluggage_list().subscribe(data=>
    {
      this.luggageList=data;
      console.log(this.luggageList);

    })
}

onsubmit()
{

  var item={"luggage_type":this.luggageForm.value.luggage_type,"luggage_weight":this.luggageForm.value.luggage_weight,"ticket_id":1}
  this.service.addluggage(item).subscribe((data:any)=>
  {
    alert("added");
  })

  this.router.navigate(['displayticket'])
}
















// @HostListener('click')
// changecolor()
// {
//   const btns = document.getElementsByClassName("btn");
// for (var i = 0; i < btns.length; i++) {
//     btns[i].addEventListener("click", function (this: any) {

//         this.style["background-color"] ='green';
//           // this.y=this.textContent
//           // console.log(this.y)
//          var x=document.getElementById('out')
//          if(x!=null)
//          x.textContent=this.textContent
//     });
//  }
// }



}
