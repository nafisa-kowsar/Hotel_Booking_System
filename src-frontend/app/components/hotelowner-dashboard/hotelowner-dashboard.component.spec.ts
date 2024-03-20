import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelownerDashboardComponent } from './hotelowner-dashboard.component';

describe('HotelownerDashboardComponent', () => {
  let component: HotelownerDashboardComponent;
  let fixture: ComponentFixture<HotelownerDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelownerDashboardComponent]
    });
    fixture = TestBed.createComponent(HotelownerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
