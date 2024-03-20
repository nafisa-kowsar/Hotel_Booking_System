import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatPaginatorModule } from '@angular/material/paginator';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { LoginComponent } from './components/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { HotelownerRegisterComponent } from './components/hotelowner-register/hotelowner-register.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HotelownerDashboardComponent } from './components/hotelowner-dashboard/hotelowner-dashboard.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { BookingComponent } from './components/booking/booking.component';
import { BookingListComponent } from './components/booking-list/booking-list.component';
import { HotelownerListComponent } from './components/hotelowner-list/hotelowner-list.component';
import { UsersListComponent } from './components/users-list/users-list.component';


import { AboutComponent } from './components/about/about.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HotelownerLoginComponent } from './components/hotelowner-login/hotelowner-login.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { OurHotelsComponent } from './components/our-hotels/our-hotels.component';
import { HotelListComponent } from './components/hotel-list/hotel-list.component';
import { UserService } from './services/user.service';
import { HotelownerService } from './services/hotelowner.service';
import { HotelService } from './services/hotel.service';
import { RoomService } from './services/room.service';

import { ManageHotelownersComponent } from './components/manage-hotelowners/manage-hotelowners.component';
import { AdminmanageBookingComponent } from './components/adminmanage-booking/adminmanage-booking.component';
import { SearchReservationByUseridComponent } from './components/search-reservation-by-userid/search-reservation-by-userid.component';
import { SearchReservationByHotelidComponent } from './components/search-reservation-by-hotelid/search-reservation-by-hotelid.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddAdminComponent } from './components/add-admin/add-admin.component';
import { AddRoomComponent } from './components/add-room/add-room.component';
import { HotelownerManageBookingComponent } from './components/hotelowner-manage-booking/hotelowner-manage-booking.component';
import { HotelownerRoomsListComponent } from './components/hotelowner-rooms-list/hotelowner-rooms-list.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { HotelownerNavbarComponent } from './components/hotelowner-navbar/hotelowner-navbar.component';
import { UserNavbarComponent } from './components/user-navbar/user-navbar.component';
import { UserHotelsListComponent } from './components/user-hotels-list/user-hotels-list.component';
import { HotelRoomsListComponent } from './components/hotel-rooms-list/hotel-rooms-list.component';
import { CheckRoomAvailibilityComponent } from './components/check-room-availibility/check-room-availibility.component';
import { UserBookingsListComponent } from './components/user-bookings-list/user-bookings-list.component';

import { PaymentDialogComponent } from './dialog/payment-dialog/payment-dialog.component';

import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { PaymentComponent } from './components/payment/payment.component';
import { ReviewComponent } from './components/review/review.component';
import { UpdateRoomComponent } from './components/update-room/update-room.component';
import { HotelownerCancelledReservationsComponent } from './components/hotelowner-cancelled-reservations/hotelowner-cancelled-reservations.component';
import { HotelownerProfileComponent } from './components/hotelowner-profile/hotelowner-profile.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { AdminHotelsListComponent } from './components/admin-hotels-list/admin-hotels-list.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    UserRegisterComponent,
    LoginComponent,
    HotelownerRegisterComponent,
    AdminDashboardComponent,
    HotelownerDashboardComponent,
    UserDashboardComponent,
    BookingComponent,
    BookingListComponent,
    HotelownerListComponent,
    UsersListComponent,


    AboutComponent,
    ProfileComponent,
    HotelownerLoginComponent,
    AdminLoginComponent,
    OurHotelsComponent,
    HotelListComponent,

    ManageHotelownersComponent,
    AdminmanageBookingComponent,
    SearchReservationByUseridComponent,
    SearchReservationByHotelidComponent,
    AddAdminComponent,
    AddRoomComponent,
    HotelownerManageBookingComponent,
    HotelownerRoomsListComponent,
    AdminNavbarComponent,
    HotelownerNavbarComponent,
    UserNavbarComponent,
    UserHotelsListComponent,
    HotelRoomsListComponent,
    CheckRoomAvailibilityComponent,
    UserBookingsListComponent,

    PaymentDialogComponent,
    PaymentComponent,
    ReviewComponent,
    UpdateRoomComponent,
    HotelownerCancelledReservationsComponent,
    HotelownerProfileComponent,
    ForgotPasswordComponent,
    AdminHotelsListComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatPaginatorModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,

  ],
  providers: [UserService, HotelownerService, HotelService, RoomService],
  bootstrap: [AppComponent]
})
export class AppModule { }
