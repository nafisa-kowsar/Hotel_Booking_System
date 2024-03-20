import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.css']
})
export class UpdateRoomComponent implements OnInit {

  roomForm!: FormGroup;
  roomId: number = 0;
  updatedRoomData: any;


  constructor(private fb: FormBuilder, private roomService: RoomService, private router: Router, private route: ActivatedRoute) {


  }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.roomId = params['roomId'];
      this.initRoomForm();
    });



  }




  initRoomForm() {
    this.roomForm = this.fb.group({
      roomSize: ['', [Validators.required, Validators.maxLength(20)]],
      bedType: ['', [Validators.required, Validators.maxLength(20), Validators.pattern('Single Bed|Double Bed|King Size Bed')]],
      maxOccupancy: ['', [Validators.required, Validators.min(1)]],
      baseFare: ['', [Validators.required, Validators.min(0.01)]],
      ac: [false, Validators.required],
      availabilityStatus: [false, Validators.required],
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

  updateRoom(data: any): void {

    console.log('updateRoomData', this.updatedRoomData);

    this.roomService.updateRoom(this.roomId, data).subscribe(
      (updatedRoom: any) => {
        console.log('Room updated successfully', updatedRoom);
        alert('Room updated successfully');
        this.router.navigate(['/hotelowner-navbar/hotelowner-rooms-list']);
      },
      error => {
        console.error('Error updating room', error);
        alert('Failed to update room. Please check the form and try again.');


      }
    );
  }

}
