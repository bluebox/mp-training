import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditserviceComponent } from './editservice.component';

describe('EditserviceComponent', () => {
  let component: EditserviceComponent;
  let fixture: ComponentFixture<EditserviceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditserviceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
