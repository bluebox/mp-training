import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerhomepageComponent } from './customerhomepage.component';

describe('CustomerhomepageComponent', () => {
  let component: CustomerhomepageComponent;
  let fixture: ComponentFixture<CustomerhomepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerhomepageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerhomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
