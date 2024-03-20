import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HotelOwner } from 'src/app/model/hotelowner';
import { HotelownerService } from 'src/app/services/hotelowner.service';
import { JwtClientHotelownerService } from 'src/app/services/jwt-client-hotelowner.service';

@Component({
  selector: 'app-hotelowner-profile',
  templateUrl: './hotelowner-profile.component.html',
  styleUrls: ['./hotelowner-profile.component.css']
})
export class HotelownerProfileComponent {

  signupForm!: FormGroup;
  hotelowner!: HotelOwner;
  passwordRequirements: string = 'Minimum length: 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character(@$!%*?&])';


  constructor(
    private formBuilder: FormBuilder,
    private hotelownerService: HotelownerService, private jwtService: JwtClientHotelownerService,
    private router: Router
  ) {
    this.signupForm = this.formBuilder.group({
      hotelOwnerName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      username: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9_]+$/)]],
      password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', [Validators.required, Validators.pattern(/^(Male|Female|Non-Binary)$/)]],
      address: ['', Validators.required],
      hotelName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      location: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      hasDining: [false, Validators.required],
      hasParking: [false, Validators.required],
      hasFreeWiFi: [false, Validators.required],
      hasRoomService: [false, Validators.required],
      hasSwimmingPool: [false, Validators.required],
      hasFitnessCenter: [false, Validators.required]


    });

    if (this.signupForm) {
      this.signupForm.valueChanges.subscribe(() => {
        this.onFormValueChanged();
      });
    }
  }


  ngOnInit(): void {

    const loggedInUser = this.jwtService.getHotelOwnerId();
    if (loggedInUser !== null) {
      this.hotelownerService.getHotelownerById(loggedInUser).subscribe(
        (data) => {
          console.log('Hotel Owner data:', data);
          this.hotelowner = data;

          this.signupForm = this.formBuilder.group({
            hotelOwnerName: [this.hotelowner.hotelOwnerName, [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
            username: [this.hotelowner.username, [Validators.required, Validators.pattern(/^[a-zA-Z0-9_]+$/)]],
            password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]],
            email: [this.hotelowner.email, [Validators.required, Validators.email]],
            gender: [this.hotelowner.gender, [Validators.required, Validators.pattern(/^(Male|Female|Non-Binary)$/)]],
            address: [this.hotelowner.address, Validators.required],
            hotelName: [this.hotelowner.hotelName, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
            location: [this.hotelowner.location, [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
            hasDining: [this.hotelowner.hasDining, Validators.required],
            hasParking: [this.hotelowner.hasParking, Validators.required],
            hasFreeWiFi: [this.hotelowner.hasFreeWiFi, Validators.required],
            hasRoomService: [this.hotelowner.hasRoomService, Validators.required],
            hasSwimmingPool: [this.hotelowner.hasSwimmingPool, Validators.required],
            hasFitnessCenter: [this.hotelowner.hasFitnessCenter, Validators.required]


          });

          this.signupForm.valueChanges.subscribe(() => {
            this.onFormValueChanged();
          });

          this.patchFormValues();
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
    }

  }

  patchFormValues() {

    this.signupForm.patchValue({
      hotelOwnerName: this.hotelowner.hotelOwnerName,
      username: this.hotelowner.username,
      email: this.hotelowner.email,
      gender: this.hotelowner.gender,
      address: this.hotelowner.address,
      hotelName: this.hotelowner.hotelName,
      location: this.hotelowner.location,
      hasDining: this.hotelowner.hasDining,
      hasParking: this.hotelowner.hasParking,
      hasFreeWiFi: this.hotelowner.hasFreeWiFi,
      hasRoomService: this.hotelowner.hasRoomService,
      hasSwimmingPool: this.hotelowner.hasSwimmingPool,
      hasFitnessCenter: this.hotelowner.hasFitnessCenter
    });
  }

  updateHotelOwner() {
    if (this.signupForm.invalid) {
      return;
    }

    const loggedInUser = this.jwtService.getHotelOwnerId();
    if (loggedInUser !== null) {

      const updatedHotelOwner: HotelOwner = {

        hotelOwnerName: this.signupForm.value.hotelOwnerName,
        username: this.signupForm.value.username,
        password: this.signupForm.value.password,
        email: this.signupForm.value.email,
        gender: this.signupForm.value.gender,
        address: this.signupForm.value.address,
        hotelName: this.signupForm.value.hotelName,
        location: this.signupForm.value.location,
        hasDining: this.signupForm.value.hasDining,
        hasParking: this.signupForm.value.hasParking,
        hasFreeWiFi: this.signupForm.value.hasFreeWiFi,
        hasRoomService: this.signupForm.value.hasRoomService,
        hasSwimmingPool: this.signupForm.value.hasSwimmingPool,
        hasFitnessCenter: this.signupForm.value.hasFitnessCenter

      };

      this.hotelownerService.updateHotelOwner(updatedHotelOwner, loggedInUser)
        .subscribe(
          hotelowner => {
            console.log('Updated:', hotelowner);

            this.signupForm.reset();


            sessionStorage.setItem('hotelOwnerName', hotelowner.hotelOwnerName);

            alert('Updated successfully!');
            this.jwtService.clearStoredToken();
            this.router.navigate(['/hotelowner-login']);
          },
          error => {
            console.log('Error:', error);
          }
        );
    } else {
      console.error('No logged-in user found.');
    }
  }







  isFieldValid(field: string) {
    return (
      !this.signupForm.get(field)?.valid &&
      this.signupForm.get(field)?.touched
    );
  }


  getErrorMessage(field: string) {
    const control = this.signupForm.get(field);
    if (control?.hasError('required')) {
      return 'This field is required';
    } else if (control?.hasError('email')) {
      return 'Invalid email format';
    } else if (control?.hasError('pattern')) {
      return 'Invalid input';
    } else if (field === 'password' && control?.hasError('invalidPassword')) {
      return this.passwordRequirements;
    }

    return '';
  }

  passwordValidator(control: any) {
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordRegex.test(control.value) ? null : { invalidPassword: true };
  }

  onFormValueChanged() {
    for (const field in this.signupForm.controls) {
      if (this.signupForm.controls.hasOwnProperty(field)) {
        this.signupForm.controls[field].markAsTouched();
      }
    }
  }




}
