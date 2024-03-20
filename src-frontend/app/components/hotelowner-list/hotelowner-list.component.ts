

import { Component, ViewChild } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hotelowner-list',
  templateUrl: './hotelowner-list.component.html',
  styleUrls: ['./hotelowner-list.component.css']
})
export class HotelownerListComponent {

  hotelownerList!: any[];
  pagedHotelownerList: any[] = [];
  pageSize: number = 4;
  currentPage: number = 0;
  totalItems: number = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private adminService: AdminService, private router: Router, private jwtAdminService: JwtClientAdminService) { }

  ngOnInit() {
    this.getAllHotelowners();
  }

  getAllHotelowners() {
    this.adminService.getHotelowners().subscribe((list) => {
      this.hotelownerList = list;
      this.totalItems = this.hotelownerList.length;
      this.onPageChange({
        pageIndex: this.currentPage, pageSize: this.pageSize,
        length: 0
      });
    });
  }

  onPageChange(event: PageEvent) {
    const startIndex = event.pageIndex * event.pageSize;
    const endIndex = startIndex + event.pageSize;
    this.pagedHotelownerList = this.hotelownerList.slice(startIndex, endIndex);
  }

  deleteHotelowner(hotelownerId: number) {
    this.adminService.deleteHotelowner(hotelownerId).subscribe((msg) => {
      console.log("Deleted " + msg);
      this.getAllHotelowners();
    });
  }

  logout(): void {
    this.jwtAdminService.clearStoredToken();
    this.router.navigate(['/admin-login']);
  }
}