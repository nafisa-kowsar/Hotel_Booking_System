import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchReservationByUseridComponent } from './search-reservation-by-userid.component';

describe('SearchReservationByUseridComponent', () => {
  let component: SearchReservationByUseridComponent;
  let fixture: ComponentFixture<SearchReservationByUseridComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchReservationByUseridComponent]
    });
    fixture = TestBed.createComponent(SearchReservationByUseridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
