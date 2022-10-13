import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlogCategorySearchComponent } from './blog-category-search.component';

describe('BlogCategorySearchComponent', () => {
  let component: BlogCategorySearchComponent;
  let fixture: ComponentFixture<BlogCategorySearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlogCategorySearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BlogCategorySearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
