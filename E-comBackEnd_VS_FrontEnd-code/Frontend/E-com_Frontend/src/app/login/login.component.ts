import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../_services/user.service';
import { UserAuthService } from '../_services/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private userService: UserService, 
    private userAuthService: UserAuthService,
    private router: Router
  ) { }

  login(loginForm: NgForm) {
    console.log("Form is active");
    console.log('PayLoad', loginForm.value);

    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        console.log('ramaa', response);
        console.log(response.jwtToken);
        console.log("dummyData", response.user.roles);

        this.userAuthService.setRoles(response.user.roles[0]);
        this.userAuthService.setToken(response.jwtToken);

        const role = response.user.roles[0].roleName;
        if(role === 'Super Admin') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/user']);
        }

      },
      (error) => {
        console.log(error);
      }
    );

  }

}
