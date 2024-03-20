import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchReservationByHotelidComponent } from './search-reservation-by-hotelid.component';

describe('SearchReservationByHotelidComponent', () => {
  let component: SearchReservationByHotelidComponent;
  let fixture: ComponentFixture<SearchReservationByHotelidComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchReservationByHotelidComponent]
    });
    fixture = TestBed.createComponent(SearchReservationByHotelidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
