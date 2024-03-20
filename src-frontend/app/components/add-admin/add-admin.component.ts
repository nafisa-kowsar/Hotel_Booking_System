import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Admin } from 'src/app/model/admin';
import { AdminService } from 'src/app/services/admin.service';
import { JwtClientAdminService } from 'src/app/services/jwt-client-admin.service';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent {

  adminForm!: FormGroup;
  passwordRequirements: string = 'Minimum length: 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character(@$!%*?&])';

  constructor(
    private formBuilder: FormBuilder,
    private adminService: AdminService,
    private router: Router, private jwtAdminService: JwtClientAdminService
  ) {

    this.adminForm = this.formBuilder.group({
      adminFirstName: ['', Validators.required],
      adminLastName: ['', Validators.required],
      username: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9_]+$/)]],
      password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]],
      email: ['', [Validators.required, Validators.email]],
    });






    if (this.adminForm) {
      this.adminForm.valueChanges.subscribe(() => {
        this.onFormValueChanged();
      });
    }
  }

  addAdmin() {
    if (this.adminForm.invalid) {
      return;
    }

    const newAdmin: Admin = {
      adminFirstName: this.adminForm.value.adminFirstName,
      adminLastName: this.adminForm.value.adminLastName,
      username: this.adminForm.value.username,
      password: this.adminForm.value.password,
      email: this.adminForm.value.email
    };


    this.adminService.postAdmin(newAdmin)
      .subscribe(
        user => {

          console.log('Inserted:', user);

          this.adminForm.reset();
          sessionStorage.setItem('adminFirstName', user.adminFirstName);
          alert('Registered successfully!');
          this.router.navigate(['/admin-login']);

        },
        error => {
          console.log('Error:', error);
          const errorMessage = error.message || 'An error occurred. Please try again later.';
          alert(errorMessage);
        }
      );

  }

  logout(): void {

    this.jwtAdminService.clearStoredToken();

    this.router.navigate(['/admin-login']);
  }


  isFieldValid(field: string) {
    const control = this.adminForm.get(field);
    return !control?.valid && control?.touched;
  }


  getErrorMessage(field: string) {
    const control = this.adminForm.get(field);
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

  onFormValueChanged() {
    for (const field in this.adminForm.controls) {
      if (this.adminForm.controls.hasOwnProperty(field)) {
        this.adminForm.controls[field].markAsTouched();
      }
    }
  }


  passwordValidator(control: any) {
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordRegex.test(control.value) ? null : { invalidPassword: true };
  }
}