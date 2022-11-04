import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayBillComponent } from './display-bill.component';

describe('DisplayBillComponent', () => {
  let component: DisplayBillComponent;
  let fixture: ComponentFixture<DisplayBillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayBillComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayBillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
