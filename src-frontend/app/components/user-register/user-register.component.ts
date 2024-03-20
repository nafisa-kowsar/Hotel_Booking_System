import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {


  signupForm!: FormGroup;
  passwordRequirements: string = 'Minimum length: 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character(@$!%*?&])';


  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {
    this.signupForm = this.formBuilder.group({
      userFirstName: ['', Validators.required],
      userLastName: ['', Validators.required],
      username: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9_]+$/)]],
      contactNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), this.passwordValidator]],
      gender: ['', [Validators.required, Validators.pattern(/^(Male|Female|Non-Binary)$/)]],
      address: ['', Validators.required]



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

    const newUser: User = {

      userFirstName: this.signupForm.value.userFirstName,
      userLastName: this.signupForm.value.userLastName,
      username: this.signupForm.value.username,
      contactNumber: this.signupForm.value.contactNumber,
      password: this.signupForm.value.password,
      email: this.signupForm.value.email,
      gender: this.signupForm.value.gender,
      address: this.signupForm.value.address

    };

    this.userService.postUser(newUser)
      .subscribe(
        user => {
          console.log("Hi");
          console.log('Inserted:', user);

          this.signupForm.reset();
          sessionStorage.setItem('userFirstName', user.userFirstName);
          alert('Registered successfully!');
          this.router.navigate(['/login']);

        },
        error => {
          console.log('Error:', error);
          const errorMessage = error.message
          alert(errorMessage);
        }
      );

  }



  isFieldValid(field: string) {
    const control = this.signupForm.get(field);
    return !control?.valid && control?.touched;
  }


  getErrorMessage(field: string) {
    const control = this.signupForm.get(field);

    if (field === 'contactNumber') {
      if (control?.hasError('required')) {
        return 'This field is required';
      } else if (control?.hasError('pattern')) {
        return 'Invalid format. Please enter a 10-digit number without spaces or special characters.';
      } else if (control?.hasError('minlength') || control?.hasError('maxlength')) {
        return 'Contact number must be 10 digits long.';
      }
    } else if (field === 'username') {
      if (control?.hasError('required')) {
        return 'This field is required';
      } else if (control?.hasError('pattern')) {
        return 'Invalid characters. Use only letters, numbers, and underscores.';
      } else if (control?.hasError('minlength') || control?.hasError('maxlength')) {
        return 'Username must be between 3 and 50 characters.';
      }
    } else if (field === 'password') {
      if (control?.hasError('required')) {
        return 'This field is required';
      } else if (control?.hasError('invalidPassword')) {
        return this.passwordRequirements;
      }
    } else {
      if (control?.hasError('required')) {
        return 'This field is required';
      } else if (control?.hasError('email')) {
        return 'Invalid email format';
      } else if (control?.hasError('pattern')) {
        return 'Invalid input';
      }
    }

    return '';
  }


  onFormValueChanged() {
    for (const field in this.signupForm.controls) {
      if (this.signupForm.controls.hasOwnProperty(field)) {
        this.signupForm.controls[field].markAsTouched();
      }
    }
  }
  passwordValidator(control: any) {
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return passwordRegex.test(control.value) ? null : { invalidPassword: true };
  }

}


