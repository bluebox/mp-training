import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpolyeeregisterpageComponent } from './empolyeeregisterpage.component';

describe('EmpolyeeregisterpageComponent', () => {
  let component: EmpolyeeregisterpageComponent;
  let fixture: ComponentFixture<EmpolyeeregisterpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpolyeeregisterpageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpolyeeregisterpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
