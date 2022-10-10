import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCusComponent } from './add-cus.component';

describe('AddCusComponent', () => {
  let component: AddCusComponent;
  let fixture: ComponentFixture<AddCusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCusComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
