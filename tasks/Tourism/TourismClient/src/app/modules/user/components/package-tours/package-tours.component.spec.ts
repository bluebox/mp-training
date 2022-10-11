import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PackageToursComponent } from './package-tours.component';

describe('PackageToursComponent', () => {
  let component: PackageToursComponent;
  let fixture: ComponentFixture<PackageToursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PackageToursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PackageToursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
