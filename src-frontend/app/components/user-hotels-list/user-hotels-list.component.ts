import { Component, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-user-hotels-list',
  templateUrl: './user-hotels-list.component.html',
  styleUrls: ['./user-hotels-list.component.css']
})
export class UserHotelsListComponent {

  search!: any;
  locationList: string[] = ['Hyderabad', 'Vizag', 'Mumbai', 'Chennai', 'Kolkata'];
  hotelsList: any[] = [];
  pagedHotelsList: any[] = [];
  itemsPerPage: number = 3;
  totalHotels: number = 0;
  pageSizeOptions: number[] = [5, 10, 20];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private hotelService: HotelService, private router: Router) { }

  ngOnInit(): void {
    this.getAllHotels();
  }

  getAllHotels(): void {
    this.hotelService.getAllHotels().subscribe(
      (data: any) => {
        this.hotelsList = data;
        console.log(this.hotelsList);
        this.totalHotels = this.hotelsList.length;
        this.updatePage();
      },
      (error) => {
        console.error('Error fetching hotels:', error);
        alert('Error fetching Hotels');
      }
    );
  }


  searchHotelByName() {
    if (this.search == null || typeof this.search !== 'string') alert("invalid Input for search by name");
    else {
      this.hotelService.getHotelsByName(this.search)
        .subscribe((list) => {
          console.log(list);
          this.hotelsList = list;
          this.totalHotels = this.hotelsList.length;
          this.updatePage();
        },
          (error) => {
            console.error('Error fetching hotels:', error);
            alert('No Hotels Found');
          });
    }

  }

  searchHotelByLocation() {
    if (this.search == null || typeof this.search !== 'string') alert("invalid Input for search by Location");
    else {
      this.hotelService.getHotelsByLocation(this.search)
        .subscribe((list) => {
          console.log(list);
          this.hotelsList = list;
          this.totalHotels = this.hotelsList.length;
          this.updatePage();
        },
          (error) => {
            console.error('Error fetching hotels:', error);
            alert('No Hotels Found');
          });
    }

  }

  goToRoom(hotelId: number, hotelName: string) {

    this.hotelService.setHotelName(hotelName)
    this.router.navigate(['/user-navbar/hotel-rooms-list', hotelId]);

  }

  updatePage(): void {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    const endIndex = startIndex + this.paginator.pageSize;
    this.pagedHotelsList = this.hotelsList.slice(startIndex, endIndex);
  }

  onPageChange(event: PageEvent): void {
    this.paginator.pageIndex = event.pageIndex;
    this.paginator.pageSize = event.pageSize;
    this.updatePage();
  }






}



