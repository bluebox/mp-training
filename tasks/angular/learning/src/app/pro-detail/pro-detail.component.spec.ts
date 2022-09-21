import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProDetailComponent } from './pro-detail.component';

describe('ProDetailComponent', () => {
  let component: ProDetailComponent;
  let fixture: ComponentFixture<ProDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
