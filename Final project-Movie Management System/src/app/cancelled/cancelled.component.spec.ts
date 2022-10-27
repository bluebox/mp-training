import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelledComponent } from './cancelled.component';

describe('CancelledComponent', () => {
  let component: CancelledComponent;
  let fixture: ComponentFixture<CancelledComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancelledComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CancelledComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
