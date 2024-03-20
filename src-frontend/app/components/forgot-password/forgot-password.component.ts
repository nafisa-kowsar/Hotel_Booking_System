import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Password } from 'src/app/model/password';
import { PasswordService } from 'src/app/services/password.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {

  constructor(private passwordService: PasswordService, private router: Router) { }

  ngOnInit() {
    window.history.pushState(null, '', window.location.href);
    window.onpopstate = function () {
      window.history.pushState(null, '', window.location.href);
    };
  }

  resetpassword(data: Password) {

    this.passwordService.newPasswordGeneration(data)
      .subscribe(() => { console.log(" password  updated"); })

    alert("Password Updated Successfully");
    this.router.navigate(['/login']);


  }


}
