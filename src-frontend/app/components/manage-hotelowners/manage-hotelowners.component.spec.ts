import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageHotelownersComponent } from './manage-hotelowners.component';

describe('ManageHotelownersComponent', () => {
  let component: ManageHotelownersComponent;
  let fixture: ComponentFixture<ManageHotelownersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageHotelownersComponent]
    });
    fixture = TestBed.createComponent(ManageHotelownersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
