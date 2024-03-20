import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HotelOwner } from 'src/app/model/hotelowner';
import { HotelownerService } from 'src/app/services/hotelowner.service';

@Component({
  selector: 'app-hotelowner-register',
  templateUrl: './hotelowner-register.component.html',
  styleUrls: ['./hotelowner-register.component.css']
})
export class HotelownerRegisterComponent {
  signupForm!: FormGroup;
  passwordRequirements: string = 'Minimum length: 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character(@$!%*?&])';

  constructor(
    private formBuilder: FormBuilder,
    private hotelownerService: HotelownerService,
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

  insertUser() {
    if (this.signupForm.invalid) {
      return;
    }

    const newHotelOwner: HotelOwner = {

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

    this.hotelownerService.postHotelOwner(newHotelOwner)
      .subscribe(
        hotelowner => {
          console.log('Inserted:', hotelowner);

          this.signupForm.reset();


          sessionStorage.setItem('hotelOwnerName', hotelowner.hotelOwnerName);

          alert('Registered successfully!');

          this.router.navigate(['/hotelowner-login']);
        },
        error => {
          console.log('Error:', error);
        }
      );
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