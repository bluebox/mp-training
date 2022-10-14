import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeerequestpageComponent } from './employeerequestpage.component';

describe('EmployeerequestpageComponent', () => {
  let component: EmployeerequestpageComponent;
  let fixture: ComponentFixture<EmployeerequestpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeerequestpageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeerequestpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
