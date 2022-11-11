import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlterusersComponent } from './alterusers.component';

describe('AlterusersComponent', () => {
  let component: AlterusersComponent;
  let fixture: ComponentFixture<AlterusersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AlterusersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlterusersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
