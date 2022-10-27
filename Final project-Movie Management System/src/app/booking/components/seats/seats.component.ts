import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HallDataService } from 'src/app/services/hall-data.service';
import { HallInterface } from 'src/app/interface/hall';
import { FormControl, FormGroup } from '@angular/forms';
import {Location} from '@angular/common';
import { ConfirmationComponent } from '../confirmation/confirmation.component';
import { BookingService } from 'src/app/services/booking.service';
import { ThisReceiver } from '@angular/compiler';


@Component({
  selector: 'app-seats',
  templateUrl: './seats.component.html',
  styleUrls: ['./seats.component.css']
})
export class SeatsComponent implements OnInit {
  Number:FormGroup=new FormGroup({
    Seat: new FormControl("")
  })
  public seats:number=0
  public hallData!:HallInterface
  public hallid:number=0
  public clicked:number=0
  public test:string[]=[]
  public price:number=0
  public selectedSeats:any
  public ss:string[]=[]
  public update:any
  public sdate!:Date
  public currentHall:any
  public rows!:number[]
  public cols!:number[]
  public number!:number
  public chosen:number=0
  public selectedSeatsN:string[] = [];
  public dummyData:boolean=false
  constructor(private router:Router,private route:ActivatedRoute,private hall:HallDataService,private _location:Location,private dialog:MatDialog,private booking:BookingService) { 
   
  }
  
  ngOnInit(): void {
    console.log("IN SEATS!!")
    this.sdate=this.hall.getDate()
    if(typeof(this.sdate)==='undefined'){
      this.router.navigate([''])
    }
    console.log("INSEATS",this.sdate)
    this.route.params.subscribe(params => {
      this.hallid=(params['hallid'])   
    })
  this.getHallData()
  this.blockSeats()
 

}


//function to get the hall data
getHallData(){
  this.hall.getSingleHall(this.hallid).subscribe((data)=>( this.hallData=data,this.rows=Array(this.hallData.rows),this.cols=Array(this.hallData.cols)))
}

blockSeats(){
  this.booking.getSelectedSeats(this.hallid).subscribe(data=>{this.selectedSeats=data,
    console.log(this.selectedSeats)
  for(let i=0;i<this.selectedSeats.length;i++){
    console.log("66",this.selectedSeats[i].Date,this.sdate)
    if(this.selectedSeats[i].Date==this.sdate){
      console.log(this.selectedSeats[i].Selected_seats)
      let temp=this.selectedSeats[i].Selected_seats.split(" ")
      for(let j=0;j<temp.length;j++){
        this.test.push(temp[j])
      }
    
  }}})
  console.log('previous',this.test)
  return this.test
}


//function to generate the hall layout 
// generateRows(){
//   this.blockSeats()
//   let pay=document.getElementById('payButton')
//   if(pay!=null){
//     pay.style.display="none"
//   }

//   let p=0
//   let basep=this.hallData.baseprice
//   console.log(this.price,this.hallData.baseprice,basep)
//     this.seats=this.Number.value.Seat
//     console.log("number of seats selected--",this.seats)
//     let form=document.getElementById("form")
//     if(form!=null){
//       form.style.display="none"
//     }
//     // document.getElementById("myBtn").disabled = true;
//     let seating=this.test
//     const body = document.getElementById('seating')
//     const btn=document.getElementById('btn')
//     btn?.setAttribute('disabled','true')
//     body!.style.backgroundColor="black"
//     body!.style.color="white"
//     let k=0
//     let count=this.seats
//     // silver_row
//     let btnidL=0
//     for(let i=0;i<2;i++){
//       let div=document.createElement('div')
//       div.setAttribute('class','div')
//       div.style.marginLeft='3vw'
//       div.style.display='flex'
//       for(let j=0;j<this.hallData.cols;j++){
//         btnidL=btnidL+1
//         let tbl = document.createElement('input');
//         tbl.type="button";
//         tbl.style.width = '20px';
//         tbl.style.height='20px';
//         tbl.style.marginRight='20px'
//         tbl.style.backgroundColor='rgb(243, 207, 198)'
//         tbl.style.marginBottom='20px'
//         tbl.style.border = '1px solid black';
//         tbl.setAttribute('id','L'+btnidL.toString())
//         tbl.addEventListener('click',function(){
//           if(tbl.style.backgroundColor==='blue'){
//             console.log("second",k,count)
//             tbl.style.backgroundColor='rgb(243, 207, 198)'
//             seating.pop()
//           }
//           if(k<count){
//           console.log("first",k,count)
//           tbl.style.backgroundColor="blue"
//           // tbl.setAttribute('disabled','true')
//           console.log(this.id)
//           p=p+(0.75*basep)
//           console.log(p)    
//           // if(this.id !in seating){
//           seating.push(this.id)
//           console.log(seating)
//           k++
//           }
//           if(k==count){
//             console.log("paying")
//             if(pay!=null){
//               pay.style.display="block"
//             }
//           }
//         }
//         )
//         for(let k=0;k<this.ss.length;k++){
//           if(tbl.id===this.ss[k]){
//             tbl.style.background='blue'
//             tbl?.setAttribute('disabled','true')
//           }
//         }
//         tbl.addEventListener('click',this.selected)
//         div.appendChild(tbl)
//       }
//     body!.appendChild(div);
//   }
//   //golden
//   let btnidG=0
//     for(let i=0;i<this.hallData.rows-4;i++){
//       let div=document.createElement('div')
//       div.style.display='flex'
//     div.style.marginLeft='3vw'
//     for(let j=0;j<this.hallData.cols;j++){
//       btnidG=btnidG+1
//       let tbl = document.createElement('input');
//       tbl.type="button";
//       tbl.style.width = '20px';
//       tbl.style.height='20px';
//       tbl.style.marginRight='20px'
//       tbl.style.backgroundColor='rgb(255, 182, 193)'
//       tbl.style.marginBottom='20px'
//       tbl.style.border = '1px solid black';
//       tbl.setAttribute('id','G'+(btnidG).toString())
//       tbl.addEventListener('click',function(){
//         if(tbl.style.backgroundColor==='blue'){
//           tbl.style.backgroundColor='rgb(255, 182, 193)'
//           seating.pop()
//         }
//         if(k<count){
//           console.log(k)
//           tbl.style.backgroundColor="blue"
//           // tbl.setAttribute('disabled','true')
//           console.log(this.id)
//           p=p+(0.75*basep)
//           console.log(p)
//           // if(this.id !in seating){
//           seating.push(this.id)
//           console.log(seating)
//           k++
//         }
//         if(k==count){
//           console.log("paying")
//           if(pay!=null){
//             pay.style.display="block"
//           }
//         }
//       }
//       )
//       for(let k=0;k<this.ss.length;k++){
//         if(tbl.id===this.ss[k]){
//           tbl.style.background='blue'
//           btn?.setAttribute('disabled','true')
//         }
//       }
//       // this.price=p
//       // console.log(this.price)
//       // tbl.addEventListener('click',this.disable)
//       div.appendChild(tbl)
//     }
//     body!.appendChild(div);
//   }
//   //premium
//   let btnidP=0
//   for(let i=0;i<2;i++){
//     let div=document.createElement('div')
//     div.style.display='flex'
//     div.style.marginLeft='3vw'
//     for(let j=0;j<this.hallData.cols;j++){
//       btnidP=btnidP+1
//       let tbl = document.createElement('input');
//       tbl.type="button";
//       tbl.style.width = '20px';
//       tbl.style.height='20px';
//       tbl.style.marginRight='20px'
//       tbl.style.backgroundColor='rgb(255, 105, 180)'
//       tbl.style.marginBottom='20px'
//       tbl.style.border = '1px solid black';
//       tbl.setAttribute('id','P'+(btnidP).toString())
//       tbl.addEventListener('click',function(){
//         if(tbl.style.backgroundColor==='blue'){
//           console.log("seats",k,count)
          
//           tbl.style.backgroundColor='rgb(255, 105, 180)'
//           seating.pop()
//         }
//         if(k<count){
//           console.log(k)
//           tbl.style.backgroundColor="blue"
//           // tbl.setAttribute('disabled','true')
//           console.log(this.id)
//           p=p+(0.75*basep)
//           console.log(p)   
//           // if(this.id !in seating){
//           seating.push(this.id)
//           console.log(seating)
//           k++
         
//           }
//         if(k==count){
//           console.log("paying")
//           if(pay!=null){
//             pay.style.display="block"
//           }
//         }})
//         for(let k=0;k<this.ss.length;k++){
//           if(tbl.id===this.ss[k]){
//             tbl.style.background='blue'
//             tbl?.setAttribute('disabled','true')
//           }
//         }
//       div.appendChild(tbl)
// }
// body!.appendChild(div);
// this.price=this.price+p
// this.test=seating
// console.log(this.price)
// console.log(this.test)
// }
// return this.test,seating,this.price,p
// }
selectseats(i:number,j:number)
{ 
  let seat=i.toString()+j.toString()
  if(this.selectedSeatsN.includes(i.toString()+j.toString())){
    let index=this.selectedSeatsN.indexOf(i.toString()+j.toString())
    this.selectedSeatsN.splice(index,1)
    console.log(this.selectedSeatsN)
    this.dummyData=true
    this.chosen--
  }
  else{
    console.log("the selected seat does not exist in the array")
    console.log("frst else, can and are",this.number,this.chosen)
    if(this.chosen<this.number){
      console.log("chosen less the number",this.chosen,this.number)
      console.log("2nd if",this.number)
      this.selectedSeatsN.push(seat)
      console.log("seat has been pushed",this.selectedSeatsN)
      this.dummyData=false
      this.chosen++
      console.log("incremented choice is",this.chosen)
    }
    else if(typeof(this.number)!=='undefined')
    {
      console.log("2nd else",this.number,this.chosen)
      this.selectedSeatsN.splice(0,1,seat)
      console.log("2nd else after replacing",this.selectedSeatsN)
    }
  }
 
 
}

generateRows(){
  console.log(this.test)
  for(let i=0;i<this.test.length;i++){
    console.log("block2")
    let id=this.test[i]
    let el=document.getElementById(id)
    console.log("element",el,id,typeof(id))
    if(el!=null){
      console.log("block3")
      el.style.backgroundColor='red'
      el.setAttribute('disabled','true')
    }
  }
  this.number=this.Number.value.Seat
}
// checkCheckBoxvalue(event:any,i:number,j:number){
//   console.log(event.checked)
//   if(event.checked){
//     let seat=i.toString()+j.toString()
//     let count=0
//     for(let v=0;v<this.ss.length;v++){
//       if(this.ss[v]===seat){
//         count++
//       }
//     }
//     if(count==0){
//       this.ss.push(seat)
//       console.log(this.ss)
//     }
    
//   }
// }

//deduct the number of seats from database
DeductSeats(){
  document.getElementById('payButton')?.setAttribute("disabled",'true')
  // this.hall.SingleHall(this.hallid,this.sdate).subscribe((data=>{this.update=data

  // this.update.T_No_Of_Seats= this.update.T_No_Of_Seats-this.number
  // console.log("data being updated in seats",this.update,this.seats)
  // this.hall.DeductSeat(this.hallid,this.sdate,this.update).subscribe(data=>console.log(data))}))
  console.log(this.test)
  // for(let i=0;i<this.seats;i++){
  //   if(this.test[i].slice(0,1)=='L'){
  //     this.price=this.price+this.hallData.baseprice*0.75
  //   }
  //   else if(this.test[i].slice(0,1)=='G'){
  //     this.price=this.price+this.hallData.baseprice
  //   }
  //   else{
  //     this.price=this.price+this.hallData.baseprice*1.5
  //   }
  // }
  this.price=this.selectedSeatsN.length*this.hallData.baseprice
  console.log(this.price)
  this.dialog.open(ConfirmationComponent,{data:{'selectedSeats':this.selectedSeatsN,'hallid':this.hallid,'price':this.price}})
}
  selected(){
    this.clicked++
    console.log(this.seats)
    if(this.seats>this.clicked)
      console.log("selected",this.clicked,)
    else{
      console.log(this.test)
      console.warn("stop")
    }
  }
  goBack(){
    window.open("http://127.0.0.1:4200/movies")

  }
}


