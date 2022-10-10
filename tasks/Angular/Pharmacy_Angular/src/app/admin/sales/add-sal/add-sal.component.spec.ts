import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSalComponent } from './add-sal.component';

describe('AddSalComponent', () => {
  let component: AddSalComponent;
  let fixture: ComponentFixture<AddSalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
