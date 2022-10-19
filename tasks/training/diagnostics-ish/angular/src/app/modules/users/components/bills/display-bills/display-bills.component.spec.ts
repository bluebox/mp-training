import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayBillsComponent } from './display-bills.component';

describe('DisplayBillsComponent', () => {
  let component: DisplayBillsComponent;
  let fixture: ComponentFixture<DisplayBillsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayBillsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayBillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
