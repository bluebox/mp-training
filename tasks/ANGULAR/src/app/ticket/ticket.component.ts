import { Component, HostListener, Input, OnInit } from '@angular/core';
import { SharedService } from 'src/app/shared.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { getLocaleNumberFormat } from '@angular/common';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {
  seat:any;
  ticketform:any=FormGroup;
  count:number=0;
  status:boolean=true;
  alltickets:any;

  user_details:any=[];

  constructor(private service:SharedService , private router:Router) {




   }


  ngOnInit(): void {
    this.service.getTicketalldetails().subscribe(data=>
      {
           this.alltickets=data;
           console.log(this.alltickets);
           for(let i of this.alltickets){
            var z=document.getElementById(i.seat_no.toString())
            console.log(i.seat_no.toString());

            if(z!=null)
              z.style.backgroundColor='green';
           }

      });

   this.ticketform= new FormGroup({
    // ticket_id:new FormControl(''),
    // flight_id: new FormControl(''),
    booking_from: new FormControl(''),
    booking_to :new FormControl(''),
    travel_date:new FormControl(''),
    // passenger_id: new FormControl('')

   },

   );

  //  this.service.getTicket().subscribe(data=>{
  //   this.user_details=data; }
  //  );
  //  console.log(this.user_details)
   this.ticketform.get('travel_date').patchValue(this.formatDate(new Date()));
  //  this.ticketform.get('ticket_id').patchValue(this.count);
  //  this.ticketform.get('flight_id').patchValue(this.user_details[0].flight_id);
  //  this.ticketform.get('passenger_id').patchValue(this.user_details[0].passenger_id);




  }


  private formatDate(date: string | number | Date) {
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;
    return [year, month, day].join('-');
  }

  // @HostListener('click')
  //  changecolor()
  //  {
  //   var seatno
  //    const btns = document.getElementsByClassName("btn");
  //  for (var i = 0; i < btns.length; i++) {
  //      btns[i].addEventListener("click", function (this: any) {

  //          this.style["background-color"] ='green';
  //             seatno=this.textContent
  //            console.log(seatno)
  //           var y=document.getElementById('out')
  //           if(y!=null)
  //           y.textContent=this.textContent
  //           this.seat=seatno
  //           setTimeout(()=>'',2000)
  //      });
  //      console.log(seatno)
  //      this.seat= seatno
  //   }


  //  }


onclick(val:any)
{

   var x=document.getElementById(val.toString())
   if(x !=null)
   {
   x.style.backgroundColor="green"
   this.seat=x.textContent
   var y=document.getElementById('out')
   if(y!=null)
       y.textContent=x.textContent

   }

}


onsubmit()
{

     console.log(this.seat);
    this.service.getTicket().subscribe(data=>{
      this.user_details=JSON.parse(JSON.stringify(data));
      var item={"ticket_id":this.user_details.ticket_id,"flight_id":this.user_details.flight_id,"booking_from":this.ticketform.value.booking_from,"booking_to":this.ticketform.value.booking_to,"travel_date":this.ticketform.value.travel_date,"passenger_id":this.user_details.passenger_id,"seat_no":this.seat
      }
      console.log(item);

      this.service.addTicket(item).subscribe(data=>
        {
          alert(data.toString())
        });
        console.log(item);
    }
     )


   this.router.navigate(['displayticket'])
   }



   changestatus()
   {
    this.status=!this.status

   }

   onsubmit_for(){
    this.router.navigate(['luggage'])
  }

}




