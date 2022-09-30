import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesViewComponent } from './vehicles-view.component';

describe('VehiclesViewComponent', () => {
  let component: VehiclesViewComponent;
  let fixture: ComponentFixture<VehiclesViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VehiclesViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehiclesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
