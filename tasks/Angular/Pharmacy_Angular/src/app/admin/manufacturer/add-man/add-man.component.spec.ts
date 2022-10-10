import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddManComponent } from './add-man.component';

describe('AddManComponent', () => {
  let component: AddManComponent;
  let fixture: ComponentFixture<AddManComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddManComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddManComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
