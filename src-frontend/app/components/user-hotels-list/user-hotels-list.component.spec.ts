import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHotelsListComponent } from './user-hotels-list.component';

describe('UserHotelsListComponent', () => {
  let component: UserHotelsListComponent;
  let fixture: ComponentFixture<UserHotelsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserHotelsListComponent]
    });
    fixture = TestBed.createComponent(UserHotelsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
