import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorUserComponent } from './error-user.component';

describe('ErrorUserComponent', () => {
  let component: ErrorUserComponent;
  let fixture: ComponentFixture<ErrorUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ErrorUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ErrorUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
