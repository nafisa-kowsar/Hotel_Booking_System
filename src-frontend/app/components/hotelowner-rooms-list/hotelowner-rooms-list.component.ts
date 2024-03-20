import { Component, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { JwtClientHotelownerService } from 'src/app/services/jwt-client-hotelowner.service';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-hotelowner-rooms-list',
  templateUrl: './hotelowner-rooms-list.component.html',
  styleUrls: ['./hotelowner-rooms-list.component.css']
})
export class HotelownerRoomsListComponent {

  roomList!: any[];

  pagedRoomList: any[] = [];
  itemsPerPage: number = 5;
  totalRooms: number = 0;



  @ViewChild(MatPaginator) paginator!: MatPaginator;



  constructor(private roomService: RoomService, private router: Router) { }

  ngOnInit() {

    this.getAllRooms();
  }

  getAllRooms() {
    this.roomService.getRooms().subscribe((list) => {
      this.roomList = list;
      this.totalRooms = this.roomList.length;
      this.updatePage();
    },
      (error) => {
        console.error('Error fetching rooms:', error);

        alert('Failed to fetch rooms. Please try again.');
      }
    );
  }

  deleteRoom(roomId: number) {

    this.roomService.removeRoom(roomId).subscribe((data) => {
      console.log("Deleted " + data);
      this.getAllRooms();
    },
      (error) => {
        console.error('Error deleting room:', error);

        alert('Failed to delete room. Please try again.');
      }
    );

  }

  updateRoom(roomId: number) {

    this.router.navigate(['/hotelowner-navbar/update-room', roomId]);

  }

  onPageChange(event: PageEvent) {
    this.paginator.pageIndex = event.pageIndex;
    this.paginator.pageSize = event.pageSize;
    this.updatePage();
  }

  updatePage() {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    const endIndex = startIndex + this.paginator.pageSize;
    this.pagedRoomList = this.roomList.slice(startIndex, endIndex);
  }





}


