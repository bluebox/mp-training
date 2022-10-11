import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEmlployeeComponent } from './add-emlployee.component';

describe('AddEmlployeeComponent', () => {
  let component: AddEmlployeeComponent;
  let fixture: ComponentFixture<AddEmlployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEmlployeeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEmlployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
