import { Component, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { AdminService } from 'src/app/services/admin.service';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent {

  usersList: any[] = [];
  pagedUsersList: any[] = [];
  pageSize: number = 4;
  currentPage: number = 0;
  totalUsers: number = 0;  // Updated property name
  pageSizeOptions: number[] = [5, 10, 20];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private adminService: AdminService, private router: Router, private jwtAdminService: JwtClientAdminService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers() {
    this.adminService.getUsers().subscribe((list) => {
      this.usersList = list;
      this.totalUsers = this.usersList.length;
      this.updatePage();
    });
  }

  updatePage() {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pagedUsersList = this.usersList.slice(startIndex, endIndex);
  }

  onPageChange(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.updatePage();
  }

  deleteUser(userId: number) {
    this.adminService.deleteUser(userId).subscribe(() => {
      console.log("Deleted user with ID: " + userId);
      this.getAllUsers();
    });
  }

  logout(): void {
    this.jwtAdminService.clearStoredToken();
    this.router.navigate(['/admin-login']);
  }
}