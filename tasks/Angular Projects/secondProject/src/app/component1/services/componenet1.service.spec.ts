import { TestBed } from '@angular/core/testing';

import { Componenet1Service } from './componenet1.service';

describe('Componenet1Service', () => {
  let service: Componenet1Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Componenet1Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
