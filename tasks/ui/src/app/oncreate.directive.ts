import { Directive } from '@angular/core';
import { ServicesService } from './services.service';
import { EventEmitter,Output } from '@angular/core';
@Directive({
  selector: '[appOncreate]'
})
export class OncreateDirective {
  device :any 
  @Output() onCreate: EventEmitter<any> = new EventEmitter<any>();
  complaintitem :any ;
  constructor(private service: ServicesService) {
    
   }
   ngOnInit() {
  //   let device_id=sessionStorage.getItem('device_id')
  //   let deviceid
  //   console.log("hello")
  //   if(device_id){
  //   let device=JSON.parse(device_id)
  // } 
  // if (deviceid) 
  //   this.service.getDeviceName(deviceid).subscribe(
  //     data =>this.device=data )
   
  //   this.onCreate.emit('dummy'); 
 } 


//   getDevicename(device_id : number){
//     console.log(device_id);
    
//     this.service.getDeviceName(device_id).subscribe(data =>this.device=data )
//     console.log(this.device);
    



// }
}
