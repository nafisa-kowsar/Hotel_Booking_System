import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelownerNavbarComponent } from './hotelowner-navbar.component';

describe('HotelownerNavbarComponent', () => {
  let component: HotelownerNavbarComponent;
  let fixture: ComponentFixture<HotelownerNavbarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelownerNavbarComponent]
    });
    fixture = TestBed.createComponent(HotelownerNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
