import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDisComponent } from './add-dis.component';

describe('AddDisComponent', () => {
  let component: AddDisComponent;
  let fixture: ComponentFixture<AddDisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDisComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddDisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
