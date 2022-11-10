import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginNavbarComponent } from './login-navbar.component';

describe('LoginNavbarComponent', () => {
  let component: LoginNavbarComponent;
  let fixture: ComponentFixture<LoginNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginNavbarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
