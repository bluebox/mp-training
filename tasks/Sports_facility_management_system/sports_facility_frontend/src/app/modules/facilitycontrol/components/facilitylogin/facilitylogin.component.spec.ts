import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityloginComponent } from './facilitylogin.component';

describe('FacilityloginComponent', () => {
  let component: FacilityloginComponent;
  let fixture: ComponentFixture<FacilityloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacilityloginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
