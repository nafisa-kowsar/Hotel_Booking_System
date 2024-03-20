import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { LoginComponent } from './components/login/login.component';
import { HotelownerRegisterComponent } from './components/hotelowner-register/hotelowner-register.component';

import { AboutComponent } from './components/about/about.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HotelownerDashboardComponent } from './components/hotelowner-dashboard/hotelowner-dashboard.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';

import { ProfileComponent } from './components/profile/profile.component';
import { HotelownerLoginComponent } from './components/hotelowner-login/hotelowner-login.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';

import { BookingComponent } from './components/booking/booking.component';
import { OurHotelsComponent } from './components/our-hotels/our-hotels.component';
import { HotelListComponent } from './components/hotel-list/hotel-list.component';

import { UsersListComponent } from './components/users-list/users-list.component';
import { HotelownerListComponent } from './components/hotelowner-list/hotelowner-list.component';
import { AdminmanageBookingComponent } from './components/adminmanage-booking/adminmanage-booking.component';
import { SearchReservationByUseridComponent } from './components/search-reservation-by-userid/search-reservation-by-userid.component';
import { SearchReservationByHotelidComponent } from './components/search-reservation-by-hotelid/search-reservation-by-hotelid.component';
import { AddAdminComponent } from './components/add-admin/add-admin.component';
import { AddRoomComponent } from './components/add-room/add-room.component';
import { HotelownerManageBookingComponent } from './components/hotelowner-manage-booking/hotelowner-manage-booking.component';
import { HotelownerRoomsListComponent } from './components/hotelowner-rooms-list/hotelowner-rooms-list.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { HotelownerNavbarComponent } from './components/hotelowner-navbar/hotelowner-navbar.component';
import { UserNavbarComponent } from './components/user-navbar/user-navbar.component';
import { AuthGuard } from './auth.guard';
import { UserHotelsListComponent } from './components/user-hotels-list/user-hotels-list.component';
import { HotelRoomsListComponent } from './components/hotel-rooms-list/hotel-rooms-list.component';
import { CheckRoomAvailibilityComponent } from './components/check-room-availibility/check-room-availibility.component';
import { UserBookingsListComponent } from './components/user-bookings-list/user-bookings-list.component';
import { PaymentComponent } from './components/payment/payment.component';
import { ReviewComponent } from './components/review/review.component';
import { UpdateRoomComponent } from './components/update-room/update-room.component';
import { HotelownerCancelledReservationsComponent } from './components/hotelowner-cancelled-reservations/hotelowner-cancelled-reservations.component';
import { HotelownerProfileComponent } from './components/hotelowner-profile/hotelowner-profile.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { AdminHotelsListComponent } from './components/admin-hotels-list/admin-hotels-list.component';

const routes: Routes = [



  {
    path: 'admin-navbar', component: AdminNavbarComponent,
    canActivate: [AuthGuard],
    data: { expectedRole: 'ADMIN' },
    children: [
      { path: 'admin-navbar', component: AdminNavbarComponent },
      { path: 'admin-dashboard', component: AdminDashboardComponent },
      { path: 'add-admin', component: AddAdminComponent },
      { path: 'users-list', component: UsersListComponent },
      { path: 'hotelowner-list', component: HotelownerListComponent },
      { path: 'admin-hotels-list', component: AdminHotelsListComponent },
      { path: 'admin-manage-booking', component: AdminmanageBookingComponent },
      { path: 'search-reservation-by-userid', component: SearchReservationByUseridComponent },
      { path: 'search-reservation-by-hotelid', component: SearchReservationByHotelidComponent },

    ],
  },






  {
    path: 'hotelowner-navbar', component: HotelownerNavbarComponent,
    canActivate: [AuthGuard],
    data: { expectedRole: 'HOTEL_OWNER' },

    children: [
      { path: 'hotelowner-navbar', component: HotelownerNavbarComponent },
      { path: 'hotelowner-dashboard', component: HotelownerDashboardComponent },
      { path: 'add-room', component: AddRoomComponent },
      { path: 'hotelowner-manage-booking', component: HotelownerManageBookingComponent },
      { path: 'hotelowner-cancelled-reservations', component: HotelownerCancelledReservationsComponent },
      { path: 'hotelowner-rooms-list', component: HotelownerRoomsListComponent },
      { path: 'update-room', component: UpdateRoomComponent },
      { path: 'update-room/:roomId', component: UpdateRoomComponent },
      { path: 'hotelowner-profile', component: HotelownerProfileComponent },


    ],
  },
  {
    path: 'user-navbar', component: UserNavbarComponent,
    canActivate: [AuthGuard],
    data: { expectedRole: 'USER' },

    children: [
      { path: 'user-navbar', component: UserNavbarComponent },
      { path: 'user-hotels-list', component: UserHotelsListComponent },
      { path: 'hotel-rooms-list', component: HotelRoomsListComponent },
      { path: 'hotel-rooms-list/:hotelId', component: HotelRoomsListComponent },
      { path: 'check-room-availability', component: CheckRoomAvailibilityComponent },
      { path: 'check-room-availability/:roomId', component: CheckRoomAvailibilityComponent },
      { path: 'user-bookings-list', component: UserBookingsListComponent },
      { path: 'user-dashboard', component: UserDashboardComponent },
      { path: 'booking', component: BookingComponent },
      { path: 'payment', component: PaymentComponent },
      { path: 'payment/:reservationId', component: PaymentComponent },
      { path: 'review/:reservationId', component: ReviewComponent },

      { path: 'profile', component: ProfileComponent },

    ],
  },


  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'hotel-list', component: HotelListComponent },
  { path: 'header', component: HeaderComponent },
  { path: 'home', component: HomeComponent },
  { path: 'footer', component: FooterComponent },
  { path: 'user-register', component: UserRegisterComponent },
  { path: 'hotelowner-register', component: HotelownerRegisterComponent },
  { path: 'ourHotels', component: OurHotelsComponent },
  { path: 'about', component: AboutComponent },
  { path: 'login', component: LoginComponent },
  { path: 'hotelowner-login', component: HotelownerLoginComponent },
  { path: 'admin-login', component: AdminLoginComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: '**', component: HomeComponent }


];






@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
