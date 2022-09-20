import { TestBed } from '@angular/core/testing';

import { DjangoService } from './django.service';

describe('DjangoService', () => {
  let service: DjangoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DjangoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
