import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviousvehiclesComponent } from './previousvehicles.component';

describe('PreviousvehiclesComponent', () => {
  let component: PreviousvehiclesComponent;
  let fixture: ComponentFixture<PreviousvehiclesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviousvehiclesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreviousvehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
