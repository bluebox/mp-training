import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPurComponent } from './add-pur.component';

describe('AddPurComponent', () => {
  let component: AddPurComponent;
  let fixture: ComponentFixture<AddPurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPurComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
