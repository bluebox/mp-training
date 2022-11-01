import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatemanagerComponent } from './createmanager.component';

describe('CreatemanagerComponent', () => {
  let component: CreatemanagerComponent;
  let fixture: ComponentFixture<CreatemanagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatemanagerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatemanagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
