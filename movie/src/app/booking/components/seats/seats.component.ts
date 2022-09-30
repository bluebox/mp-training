import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HallDataService } from 'src/app/services/hall-data.service';
import { HallInterface } from 'src/app/interface/hall';
import { FormControl, FormGroup } from '@angular/forms';
import {Location} from '@angular/common';
import { ConfirmationComponent } from '../confirmation/confirmation.component';


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

  constructor(private router:Router,private route:ActivatedRoute,private hall:HallDataService,private _location:Location,private dialog:MatDialog) { }
  
  ngOnInit(): void {
    // console.log(typeof(this.hallid))
    this.route.params.subscribe(params => {
    this.hallid=(params['hallid'])
    // this.hall.getSingleHall(this.hallid).subscribe((data)=>{this.hallData=data
    // console.log(this.hallData)
    // console.log(this.hallData.T_No_Of_Seats)
    // this.generateRows(this.hallData.rows,this.hallData.cols)
    // this.DeductSeats(this.hallid,this.hallData,this.seats)
    // }
    // )
  })
  this.getHallData()

}


//function to get the hall data
getHallData(){
  this.hall.getSingleHall(this.hallid).subscribe((data)=>( this.hallData=data))

 
}



//function to generate the hall layout 
generateRows(){
  let p=0
  let basep=this.hallData.baseprice
  console.log(this.price,this.hallData.baseprice,basep)
    this.seats=this.Number.value.Seat
    console.log("number of seats selected--",this.seats)
    let form=document.getElementById("form")
    if(form!=null){
      form.style.display="none"
    }
    // document.getElementById("myBtn").disabled = true;
    let seating=this.test
    const body = document.body
    const btn=document.getElementById('btn')
    btn?.setAttribute('disabled','true')
    body.style.backgroundColor="black"
    body.style.color="white"
    let k=0
    let count=this.seats
    // silver_row
    let btnidL=0
    for(let i=0;i<2;i++){
      let div=document.createElement('div')
      div.style.marginLeft='33vw'
      div.style.display='flex'
      for(let j=0;j<this.hallData.cols;j++){
        btnidL=btnidL+1
      let tbl = document.createElement('input');
      tbl.type="button";
      tbl.style.width = '20px';
      tbl.style.height='20px';
      tbl.style.marginRight='20px'
      tbl.style.backgroundColor='rgb(243, 207, 198)'
      tbl.style.marginBottom='20px'
      tbl.style.border = '1px solid black';
      tbl.setAttribute('id','L'+btnidL.toString())
      tbl.addEventListener('click',function(){
        if(k<count){
        console.log(k)
        tbl.style.backgroundColor="blue"
        tbl.setAttribute('disabled','true')
        console.log(this.id)
        p=p+(0.75*basep)
        console.log(p)    
        // if(this.id !in seating){
        seating.push(this.id)
        console.log(seating)
        k++
        }}
      )
      tbl.addEventListener('click',this.selected)
      div.appendChild(tbl)
    }
    body.appendChild(div);
  }
  //golden
  let btnidG=0
    for(let i=0;i<this.hallData.rows-4;i++){
      let div=document.createElement('div')
      div.style.display='flex'
    div.style.marginLeft='33vw'
    for(let j=0;j<this.hallData.cols;j++){
      btnidG=btnidG+1
      let tbl = document.createElement('input');
      tbl.type="button";
      tbl.style.width = '20px';
      tbl.style.height='20px';
      tbl.style.marginRight='20px'
      tbl.style.backgroundColor='rgb(255, 182, 193)'
      tbl.style.marginBottom='20px'
      tbl.style.border = '1px solid black';
      tbl.setAttribute('id','G'+(btnidG).toString())
      tbl.addEventListener('click',function(){
        if(k<count){
          console.log(k)
          tbl.style.backgroundColor="blue"
          tbl.setAttribute('disabled','true')
          console.log(this.id)
          p=p+(0.75*basep)
          console.log(p)
          // if(this.id !in seating){
          seating.push(this.id)
          console.log(seating)
          k++
        }
      }
      )
      // this.price=p
      // console.log(this.price)
      // tbl.addEventListener('click',this.disable)
      div.appendChild(tbl)
    }
    body.appendChild(div);
  }
  //premium
  let btnidP=0
  for(let i=0;i<2;i++){
    let div=document.createElement('div')
    div.style.display='flex'
    div.style.marginLeft='33vw'
    for(let j=0;j<this.hallData.cols;j++){
      btnidP=btnidP+1
      let tbl = document.createElement('input');
      tbl.type="button";
      tbl.style.width = '20px';
      tbl.style.height='20px';
      tbl.style.marginRight='20px'
      tbl.style.backgroundColor='rgb(255, 105, 180)'
      tbl.style.marginBottom='20px'
      tbl.style.border = '1px solid black';
      tbl.setAttribute('id','P'+(btnidP).toString())
      tbl.addEventListener('click',function(){
        if(k<count){
          console.log(k)
          tbl.style.backgroundColor="blue"
          tbl.setAttribute('disabled','true')
          console.log(this.id)
          p=p+(0.75*basep)
          console.log(p)   
          // if(this.id !in seating){
          seating.push(this.id)
          console.log(seating)
          k++
          }})
      // tbl.addEventListener('click',this.disable)
      div.appendChild(tbl)
}
body.appendChild(div);
this.price=this.price+p
this.test=seating
console.log(this.price)
console.log(this.test)
}
return this.test,seating,this.price,p
}
RecordSeats(){
    
    
  }
  DeductSeats(){
    this.hallData.T_No_Of_Seats=100-this.seats
    this.hall.updateHall(this.hallid,this.hallData).subscribe(data=>console.log(data))
    console.log(this.test)
    for(let i=0;i<this.seats;i++){
      if(this.test[i].slice(0,1)=='L'){
        this.price=this.price+this.hallData.baseprice*0.75
      }
      else if(this.test[i].slice(0,1)=='G'){
        this.price=this.price+this.hallData.baseprice
      }
      else{
        this.price=this.price+this.hallData.baseprice*1.5
      }
    }
    console.log(this.price)
    this.dialog.open(ConfirmationComponent,{data:{'selectedSeats':this.test,'hallid':this.hallid,'price':this.price}})
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
    this._location.back();

  }
}


