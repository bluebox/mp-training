import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customerhomepage',
  templateUrl: './customerhomepage.component.html',
  styleUrls: ['./customerhomepage.component.css']
})
export class CustomerhomepageComponent implements OnInit {
element:any

list:any = ["https://assets.materialup.com/uploads/9089f6bc-8ba1-41fe-b71f-2eae7a65f40f/preview.jpg",
            "https://cdn.dribbble.com/users/6061337/screenshots/15182925/media/39f46288b427beb1e5c3035236de2e26.png?compress=1&resize=400x300",
            "https://cdn.dribbble.com/users/6061337/screenshots/15947672/media/d6f96ea14630dc9b0a4862d71a6468c4.png?compress=1&resize=700x525&vertical=top",
            "https://cdn.dribbble.com/users/4214551/screenshots/16278560/media/61d24270f3fd861d90069c50903b1a88.jpg?compress=1&resize=700x525&vertical=top",
            "https://cdn.dribbble.com/users/917664/screenshots/15886918/media/a2a281f268000855db208c60d90c3089.png?compress=1&resize=700x525&vertical=top"]
  constructor() { 
    this.element = "https://wallpaperaccess.com/full/2311974.jpg"

    let i = 0
    let id = setInterval(()=>{
   
    
        this.element = this.list[i]
        i += 1
        if (i === this.list.length ){
          i = 0
          this.element = "https://wallpaperaccess.com/full/2311974.jpg"
        }
          
          
        
  

 

    },3000)
  }



  ngOnInit(): void {
  }

}
