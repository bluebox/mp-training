import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreelanceRegistrationComponent } from './freelance-registration.component';

describe('FreelanceRegistrationComponent', () => {
  let component: FreelanceRegistrationComponent;
  let fixture: ComponentFixture<FreelanceRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FreelanceRegistrationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FreelanceRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
