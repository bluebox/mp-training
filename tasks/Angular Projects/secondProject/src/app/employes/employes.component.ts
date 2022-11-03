import { Component, OnInit, Self } from '@angular/core';
import { Componenet1Service } from '../component1/services/componenet1.service';

@Component({
  selector: 'app-employes',
  templateUrl: './employes.component.html',
  styleUrls: ['./employes.component.scss'],
  providers:[Componenet1Service]
})
export class EmployesComponent implements OnInit {
empName:string="sai Kumar";
constructor(@Self() private roomservice:Componenet1Service) { }


  ngOnInit(): void {
  }

}
