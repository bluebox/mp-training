import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DownloadsPdfComponent } from './downloads-pdf.component';

describe('DownloadsPdfComponent', () => {
  let component: DownloadsPdfComponent;
  let fixture: ComponentFixture<DownloadsPdfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DownloadsPdfComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DownloadsPdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
