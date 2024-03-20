import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelRoomsListComponent } from './hotel-rooms-list.component';

describe('HotelRoomsListComponent', () => {
  let component: HotelRoomsListComponent;
  let fixture: ComponentFixture<HotelRoomsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelRoomsListComponent]
    });
    fixture = TestBed.createComponent(HotelRoomsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
