import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {

  constructor() {
    console.log('admin dashboard component');
  }



}
