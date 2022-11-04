import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemRegisterComponent } from './item-register.component';

describe('ItemRegisterComponent', () => {
  let component: ItemRegisterComponent;
  let fixture: ComponentFixture<ItemRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemRegisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
