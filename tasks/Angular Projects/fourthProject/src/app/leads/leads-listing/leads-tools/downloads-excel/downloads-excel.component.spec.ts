import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DownloadsExcelComponent } from './downloads-excel.component';

describe('DownloadsExcelComponent', () => {
  let component: DownloadsExcelComponent;
  let fixture: ComponentFixture<DownloadsExcelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DownloadsExcelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DownloadsExcelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
