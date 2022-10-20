import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnBillComponent } from './own-bill.component';

describe('OwnBillComponent', () => {
  let component: OwnBillComponent;
  let fixture: ComponentFixture<OwnBillComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnBillComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnBillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
