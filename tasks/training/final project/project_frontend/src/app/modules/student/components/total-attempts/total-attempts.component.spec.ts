import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalAttemptsComponent } from './total-attempts.component';

describe('TotalAttemptsComponent', () => {
  let component: TotalAttemptsComponent;
  let fixture: ComponentFixture<TotalAttemptsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TotalAttemptsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TotalAttemptsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
