import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoneytransferComponent } from './moneytransfer.component';

describe('MoneytransferComponent', () => {
  let component: MoneytransferComponent;
  let fixture: ComponentFixture<MoneytransferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MoneytransferComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MoneytransferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
