import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelownerListComponent } from './hotelowner-list.component';

describe('HotelownerListComponent', () => {
  let component: HotelownerListComponent;
  let fixture: ComponentFixture<HotelownerListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelownerListComponent]
    });
    fixture = TestBed.createComponent(HotelownerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
