import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FcontractDetailsComponent } from './fcontract-details.component';

describe('FcontractDetailsComponent', () => {
  let component: FcontractDetailsComponent;
  let fixture: ComponentFixture<FcontractDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FcontractDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FcontractDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
