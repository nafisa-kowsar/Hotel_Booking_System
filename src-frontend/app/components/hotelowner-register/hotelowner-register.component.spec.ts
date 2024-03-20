import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelownerRegisterComponent } from './hotelowner-register.component';

describe('HotelownerRegisterComponent', () => {
  let component: HotelownerRegisterComponent;
  let fixture: ComponentFixture<HotelownerRegisterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelownerRegisterComponent]
    });
    fixture = TestBed.createComponent(HotelownerRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
