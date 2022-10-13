import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditbranchComponent } from './editbranch.component';

describe('EditbranchComponent', () => {
  let component: EditbranchComponent;
  let fixture: ComponentFixture<EditbranchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditbranchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditbranchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
