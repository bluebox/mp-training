import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryViewComponent } from './delivery-view.component';

describe('DeliveryViewComponent', () => {
  let component: DeliveryViewComponent;
  let fixture: ComponentFixture<DeliveryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeliveryViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeliveryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
