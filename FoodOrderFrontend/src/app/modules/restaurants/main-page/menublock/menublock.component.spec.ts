import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenublockComponent } from './menublock.component';

describe('MenublockComponent', () => {
  let component: MenublockComponent;
  let fixture: ComponentFixture<MenublockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenublockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenublockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
