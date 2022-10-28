import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Menu } from 'src/app/interfaces/menu';
import { MenuService } from 'src/app/services/menu.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  checked = false;
  public menuItem:Menu[]=[];
  constructor(private fb:FormBuilder, private menu:MenuService) { }

  ngOnInit(): void {
    this.getData()
    
  }

  menuForm=this.fb.group(
    {
    menu_id:['', Validators.required],
    menu_type:['', Validators.required],
    restaurant_id :['', Validators.required],
    is_available :[this.checked],
    }
  );

getData()
{
  this.menu.getMenu().subscribe(data=>{
    console.log(data);this.menuItem=data
  ;console.log(this.menuItem)})

}

onSubmit(){
    console.log(this.menuForm.value)
    
    if (this.menuForm.valid) {
      this.menu.postMenu(this.menuForm.value).subscribe((data)=>{
        console.log(data)
      })
      console.log('form submitted');
      console.log(this.menuForm.value)
    } else {
      console.log('notttt form submitted');
    }
    
    this.menuForm.reset()
  }
}
