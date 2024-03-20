import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrls: ['./add-room.component.css']
})
export class AddRoomComponent {

  roomForm!: FormGroup;


  constructor(private fb: FormBuilder, private roomService: RoomService, private router: Router) { }

  ngOnInit() {
    this.initRoomForm();
  }

  initRoomForm() {
    this.roomForm = this.fb.group({
      roomSize: ['', [Validators.required, Validators.maxLength(20)]],
      bedType: ['', [Validators.required, Validators.maxLength(20), Validators.pattern('Single Bed|Double Bed|King Size Bed')]],
      maxOccupancy: ['', [Validators.required, Validators.min(1)]],
      baseFare: ['', [Validators.required, Validators.min(0.01)]],
      ac: [null, Validators.required],
      availabilityStatus: [null, Validators.required],
    });
  }

  isFieldValid(field: string) {
    const control = this.roomForm.get(field);
    return control && control.invalid && (control.dirty || control.touched);
  }

  getErrorMessage(field: string) {
    const control = this.roomForm.get(field);
    if (control) {
      if (control.hasError('required')) {
        return 'This field is required';
      } else if (control.hasError('min')) {
        return 'Must be a positive number';
      }
    }
    return '';
  }

  addRoom(data: any) {


    this.roomService.addRoom(data).subscribe(room => {
      console.log(room);
      alert('Room added successfully');
      this.router.navigate(['/hotelowner-navbar/hotelowner-rooms-list']);
    },
      error => {
        console.error('Error adding room:', error);
        alert('Failed to add room. Please check the form and try again.');
      }
    );





  }




}
