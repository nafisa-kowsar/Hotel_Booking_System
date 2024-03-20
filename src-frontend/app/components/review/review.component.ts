import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewDTO } from 'src/app/model/reviewDTO';
import { JwtClientService } from 'src/app/services/jwt-client.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {


  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwtToken');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }
  reservationId: number = 0;

  reviewDTO: ReviewDTO = { rating: 0, reviewText: '' };
  text: string = '';

  constructor(private route: ActivatedRoute, private http: HttpClient, private jwtService: JwtClientService, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.reservationId = +params['reservationId'];

    });
  }

  addReview(): void {

    this.reviewDTO.reviewDate = new Date();
    console.log(this.reviewDTO.reviewDate);
    this.reviewDTO.reviewText = this.text;
    console.log('Rating:', this.reviewDTO.rating);
    console.log('Review Text:', this.text);

    const loggedInUser = this.jwtService.getUserId();
    console.log(loggedInUser);

    if (loggedInUser !== null) {





      const url = `http://localhost:8081/api/review/add/${loggedInUser}/${this.reservationId}`;
      this.http.post<string>(url, this.reviewDTO, { headers: this.getHeaders() }).
        subscribe(
          (response: any) => {
            console.log('Review added successfully:', response);
            alert('Review added successfully');
            this.router.navigate(['/user-navbar/user-dashboard']);

          },
          (error: any) => {
            console.error('Error adding review:', error);
            alert('Failed to add review. Please check the form and try again.');
          }
        );
    } else {
      console.error('No logged-in user found.');
    }
  }



}