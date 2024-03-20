import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminmanageBookingComponent } from './adminmanage-booking.component';

describe('AdminmanageBookingComponent', () => {
  let component: AdminmanageBookingComponent;
  let fixture: ComponentFixture<AdminmanageBookingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminmanageBookingComponent]
    });
    fixture = TestBed.createComponent(AdminmanageBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
