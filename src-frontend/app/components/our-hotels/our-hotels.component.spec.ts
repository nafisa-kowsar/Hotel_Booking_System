import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OurHotelsComponent } from './our-hotels.component';

describe('OurHotelsComponent', () => {
  let component: OurHotelsComponent;
  let fixture: ComponentFixture<OurHotelsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OurHotelsComponent]
    });
    fixture = TestBed.createComponent(OurHotelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
