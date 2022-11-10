import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddvehicleComponent } from './addvehicle.component';

describe('AddvehicleComponent', () => {
  let component: AddvehicleComponent;
  let fixture: ComponentFixture<AddvehicleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddvehicleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddvehicleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
