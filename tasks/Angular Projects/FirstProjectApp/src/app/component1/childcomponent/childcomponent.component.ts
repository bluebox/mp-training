import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { emp } from '../component';

@Component({
  selector: 'app-childcomponent',
  templateUrl: './childcomponent.component.html',
  styleUrls: ['./childcomponent.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ChildcomponentComponent implements OnInit, OnChanges{
  @Input() childemp:emp[]=[];
  @Input() title:string = '';
  @Output() moveparentemp=new EventEmitter<emp>();
    constructor() { }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if (changes['title']){
      this.title = changes['title'].currentValue.toUpperCase();
    }
  }
    bool1=false;
  ngOnInit(): void {
  }

exp!:emp;

fun1(i:emp){
this.bool1=!this.bool1
this.moveparentemp.emit(i)

}
}
