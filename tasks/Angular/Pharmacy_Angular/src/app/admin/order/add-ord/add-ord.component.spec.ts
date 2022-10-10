import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOrdComponent } from './add-ord.component';

describe('AddOrdComponent', () => {
  let component: AddOrdComponent;
  let fixture: ComponentFixture<AddOrdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOrdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddOrdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
