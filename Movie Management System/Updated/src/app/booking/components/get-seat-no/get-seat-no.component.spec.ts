import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetSeatNoComponent } from './get-seat-no.component';

describe('GetSeatNoComponent', () => {
  let component: GetSeatNoComponent;
  let fixture: ComponentFixture<GetSeatNoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetSeatNoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetSeatNoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
