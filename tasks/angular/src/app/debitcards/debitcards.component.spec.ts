import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DebitcardsComponent } from './debitcards.component';

describe('DebitcardsComponent', () => {
  let component: DebitcardsComponent;
  let fixture: ComponentFixture<DebitcardsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DebitcardsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DebitcardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
