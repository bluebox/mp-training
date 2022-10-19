import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewReportComponent } from './new-report.component';

describe('NewReportComponent', () => {
  let component: NewReportComponent;
  let fixture: ComponentFixture<NewReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
