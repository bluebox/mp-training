import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatecustomeraccountComponent } from './createcustomeraccount.component';

describe('CreatecustomeraccountComponent', () => {
  let component: CreatecustomeraccountComponent;
  let fixture: ComponentFixture<CreatecustomeraccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatecustomeraccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatecustomeraccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
