import { TestBed } from '@angular/core/testing';

import { JwtClientHotelownerService } from './jwt-client-hotelowner.service';

describe('JwtClientHotelownerService', () => {
  let service: JwtClientHotelownerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JwtClientHotelownerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
