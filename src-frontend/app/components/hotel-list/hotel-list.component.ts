import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-hotel-list',
  templateUrl: './hotel-list.component.html',
  styleUrls: ['./hotel-list.component.css']
})
export class HotelListComponent {

  hotels: any[] = [];



  constructor(private hotelService: HotelService, private router: Router) { }

  ngOnInit(): void {

    this.hotelService.getAllHotels().subscribe(
      (data: any) => {
        this.hotels = data;
      },
      (error) => {
        console.error('Error fetching hotels:', error);
      }
    );
  }

  goToLogin() {

    this.router.navigate(['/login']);
  }

}
