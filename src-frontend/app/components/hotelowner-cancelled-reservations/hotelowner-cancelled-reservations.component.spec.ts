import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelownerCancelledReservationsComponent } from './hotelowner-cancelled-reservations.component';

describe('HotelownerCancelledReservationsComponent', () => {
  let component: HotelownerCancelledReservationsComponent;
  let fixture: ComponentFixture<HotelownerCancelledReservationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelownerCancelledReservationsComponent]
    });
    fixture = TestBed.createComponent(HotelownerCancelledReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
