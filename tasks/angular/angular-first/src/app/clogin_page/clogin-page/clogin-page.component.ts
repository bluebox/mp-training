import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';

@Component({
  selector: 'app-clogin-page',
  templateUrl: './clogin-page.component.html',
  styleUrls: ['./clogin-page.component.css']
})
export class CloginPageComponent implements OnInit {

  constructor(private service : ServiceService ) { }
  client_id: any = window.sessionStorage.getItem('cuser')
  client_id_parse = JSON.parse(this.client_id)
  ngOnInit(): void {
    this.service.getContractOfClient(this.client_id_parse.client_id).subscribe((data: any) => {
    sessionStorage.setItem('contractDetails', JSON.stringify(data));
  });
  }
  data : any = window.sessionStorage.getItem('cuser');
  parse_data = JSON.parse(this.data)

}
