import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from '../interfaces/food';
import { Menu } from '../interfaces/menu';
import { MenuItems } from '../interfaces/menuItems';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private url:string="http://127.0.0.1:8000/menu/add"
  private urlList:string="http://127.0.0.1:8000/menu/addfood"
  constructor(private http:HttpClient) { }



  getMenu():Observable<Menu[]>{
    return this.http.get<Menu[]>(this.url)
  }

  postMenu(data:object)
  {
    return this.http.post<Menu>(this.url,data)
  }
 
postList(data:object)
{
  return this.http.post<MenuItems>(this.urlList,data)
}

}
