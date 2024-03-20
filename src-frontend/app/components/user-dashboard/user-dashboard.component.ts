

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  searchForm!: FormGroup;
  searchResults: any[] = [];
  todayDate: string = new Date().toISOString().split('T')[0];

  constructor(private fb: FormBuilder, private roomService: RoomService, private router: Router) { }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      location: ['', Validators.required],
      checkInDate: ['', Validators.required],
      checkOutDate: ['', [Validators.required]]
    }, {
      validators: [this.dateValidator]
    });
  }

  get f() {
    return this.searchForm.controls;
  }

  onSubmit(): void {
    if (this.searchForm.valid) {
      const { location, checkInDate, checkOutDate } = this.searchForm.value;
      this.roomService.searchRooms(location, checkInDate, checkOutDate).subscribe(
        (data) => {
          this.searchResults = data;
          console.log('Search Results:', this.searchResults);

          if (this.searchResults.length === 0) {
            console.log('No rooms found for the selected dates and location.');
          }
        },
        (error) => {
          console.error('Error fetching search results:', error);
          alert('Error fetching search results. Try changing Check-In and Check-Out dates');
        }
      );
    }
  }
  goToHotels() {
    this.router.navigate(['/user-navbar/user-hotels-list']);
  }

  dateValidator(group: FormGroup) {
    const checkInDate = group.get('checkInDate')?.value;
    const checkOutDate = group.get('checkOutDate')?.value;

    if (checkInDate && checkOutDate && new Date(checkOutDate) <= new Date(checkInDate)) {
      group.get('checkOutDate')?.setErrors({ 'invalidDate': true });
    } else {
      group.get('checkOutDate')?.setErrors(null);
    }
  }
}
