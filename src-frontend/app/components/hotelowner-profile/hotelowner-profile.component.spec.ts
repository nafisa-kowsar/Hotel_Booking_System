import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelownerProfileComponent } from './hotelowner-profile.component';

describe('HotelownerProfileComponent', () => {
  let component: HotelownerProfileComponent;
  let fixture: ComponentFixture<HotelownerProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelownerProfileComponent]
    });
    fixture = TestBed.createComponent(HotelownerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
