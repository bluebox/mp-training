import { Component, OnInit } from '@angular/core';
import { HttpserviceService } from 'src/app/httpservice.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent implements OnInit {

  subscription : Subscription = Subscription.EMPTY
  services: any;
  displayedColumns :string[]=['spa','service_id','service_name','Amount_to_be_paid','update','delete'];

  constructor(private http : HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getServices().subscribe((data) =>{this.services = data ;console.log(data)});
  }

  deleteService(arg :any){
    if(confirm("do u want to delete")){
      var serviceVal = {"service_id": arg}
      console.log(serviceVal)
      this.http.deleteServices(serviceVal).subscribe(result=>{
        console.log(result)
        alert(result.toString());
        console.log(serviceVal);
        window.location.reload();
      })
  }

  }
}
