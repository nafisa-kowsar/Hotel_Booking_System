import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelownerManageBookingComponent } from './hotelowner-manage-booking.component';

describe('HotelownerManageBookingComponent', () => {
  let component: HotelownerManageBookingComponent;
  let fixture: ComponentFixture<HotelownerManageBookingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelownerManageBookingComponent]
    });
    fixture = TestBed.createComponent(HotelownerManageBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
