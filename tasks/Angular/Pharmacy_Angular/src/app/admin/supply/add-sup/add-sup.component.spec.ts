import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSupComponent } from './add-sup.component';

describe('AddSupComponent', () => {
  let component: AddSupComponent;
  let fixture: ComponentFixture<AddSupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
