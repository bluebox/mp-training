import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorHomeComponent } from './doctor-home.component';

describe('DoctorHomeComponent', () => {
  let component: DoctorHomeComponent;
  let fixture: ComponentFixture<DoctorHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
