import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesUpdateComponent } from './vehicles-update.component';

describe('VehiclesUpdateComponent', () => {
  let component: VehiclesUpdateComponent;
  let fixture: ComponentFixture<VehiclesUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VehiclesUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VehiclesUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
