import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewbranchComponent } from './newbranch.component';

describe('NewbranchComponent', () => {
  let component: NewbranchComponent;
  let fixture: ComponentFixture<NewbranchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewbranchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewbranchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
