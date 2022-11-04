import { TestBed } from '@angular/core/testing';

import { MyguardGuard } from './myguard.guard';

describe('MyguardGuard', () => {
  let guard: MyguardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(MyguardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
