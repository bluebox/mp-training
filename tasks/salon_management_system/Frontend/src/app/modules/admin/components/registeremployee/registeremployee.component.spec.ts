import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisteremployeeComponent } from './registeremployee.component';

describe('RegisteremployeeComponent', () => {
  let component: RegisteremployeeComponent;
  let fixture: ComponentFixture<RegisteremployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisteremployeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisteremployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
