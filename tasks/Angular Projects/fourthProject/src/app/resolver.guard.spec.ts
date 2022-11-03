import { TestBed } from '@angular/core/testing';

import { ResolverGuard } from './resolver.guard';

describe('ResolverGuard', () => {
  let guard: ResolverGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(ResolverGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
