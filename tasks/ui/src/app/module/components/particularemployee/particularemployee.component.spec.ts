import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticularemployeeComponent } from './particularemployee.component';

describe('ParticularemployeeComponent', () => {
  let component: ParticularemployeeComponent;
  let fixture: ComponentFixture<ParticularemployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticularemployeeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParticularemployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
