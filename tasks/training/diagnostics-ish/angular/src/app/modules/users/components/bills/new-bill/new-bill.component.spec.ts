import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewBillComponent } from './new-bill.component';

describe('NewBillComponent', () => {
  let component: NewBillComponent;
  let fixture: ComponentFixture<NewBillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewBillComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewBillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
