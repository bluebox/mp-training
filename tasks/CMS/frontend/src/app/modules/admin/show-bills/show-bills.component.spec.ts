import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowBillsComponent } from './show-bills.component';

describe('ShowBillsComponent', () => {
  let component: ShowBillsComponent;
  let fixture: ComponentFixture<ShowBillsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowBillsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowBillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
