import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustommoduleComponent } from './custommodule.component';

describe('CustommoduleComponent', () => {
  let component: CustommoduleComponent;
  let fixture: ComponentFixture<CustommoduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustommoduleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustommoduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
