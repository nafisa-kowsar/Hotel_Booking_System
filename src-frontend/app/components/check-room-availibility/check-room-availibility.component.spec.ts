import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckRoomAvailibilityComponent } from './check-room-availibility.component';

describe('CheckRoomAvailibilityComponent', () => {
  let component: CheckRoomAvailibilityComponent;
  let fixture: ComponentFixture<CheckRoomAvailibilityComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CheckRoomAvailibilityComponent]
    });
    fixture = TestBed.createComponent(CheckRoomAvailibilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
