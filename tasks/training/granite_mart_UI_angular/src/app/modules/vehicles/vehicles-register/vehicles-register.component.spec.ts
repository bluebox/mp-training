import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesRegisterComponent } from './vehicles-register.component';

describe('VehiclesRegisterComponent', () => {
  let component: VehiclesRegisterComponent;
  let fixture: ComponentFixture<VehiclesRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VehiclesRegisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehiclesRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
