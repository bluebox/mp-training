import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPreComponent } from './add-pre.component';

describe('AddPreComponent', () => {
  let component: AddPreComponent;
  let fixture: ComponentFixture<AddPreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
