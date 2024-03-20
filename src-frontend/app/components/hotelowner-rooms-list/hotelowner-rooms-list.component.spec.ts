import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelownerRoomsListComponent } from './hotelowner-rooms-list.component';

describe('HotelownerRoomsListComponent', () => {
  let component: HotelownerRoomsListComponent;
  let fixture: ComponentFixture<HotelownerRoomsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelownerRoomsListComponent]
    });
    fixture = TestBed.createComponent(HotelownerRoomsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
