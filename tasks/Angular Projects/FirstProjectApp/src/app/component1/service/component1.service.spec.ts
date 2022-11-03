import { TestBed } from '@angular/core/testing';

import { Component1Service } from './component1.service';

describe('Component1Service', () => {
  let service: Component1Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Component1Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
