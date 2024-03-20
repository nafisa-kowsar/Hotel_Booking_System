import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-our-hotels',
  templateUrl: './our-hotels.component.html',
  styleUrls: ['./our-hotels.component.css']
})
export class OurHotelsComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {


  }

  goToLogin() {



    console.log('Navigating to login...');
    this.router.navigate(['/login']);
  }

}






