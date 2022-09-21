import { Component } from "@angular/core";


@Component({
     selector: 'app-server',
     templateUrl: './server.component.html'
     
})
export class ServerComponent{
      number1: Number = 10
      str1: String ="hello"
     isIt= true
     name1="hi";
      getServerStatus(){
          return this.str1;
      }

      constructor(){
          setTimeout(() =>{
               this.isIt =false
          }, 20000)
      }
      onClick(){
          this.isIt = !this.isIt
      }
      onUpdate(event:any){
          this.name1 = (<HTMLInputElement>event.target).value
      }
}