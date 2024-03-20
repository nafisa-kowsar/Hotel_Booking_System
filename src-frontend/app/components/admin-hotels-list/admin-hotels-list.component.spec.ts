import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminHotelsListComponent } from './admin-hotels-list.component';

describe('AdminHotelsListComponent', () => {
  let component: AdminHotelsListComponent;
  let fixture: ComponentFixture<AdminHotelsListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminHotelsListComponent]
    });
    fixture = TestBed.createComponent(AdminHotelsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
