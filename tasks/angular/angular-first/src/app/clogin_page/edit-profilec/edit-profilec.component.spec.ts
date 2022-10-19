import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfilecComponent } from './edit-profilec.component';

describe('EditProfilecComponent', () => {
  let component: EditProfilecComponent;
  let fixture: ComponentFixture<EditProfilecComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditProfilecComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditProfilecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
