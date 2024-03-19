import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServiceService } from '../../shared/login-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {
  email!: string;
  password!: string;

  constructor(private router: Router, private loginService: LoginServiceService) {}

  login(): void {
    this.loginService.login(this.email, this.password).subscribe(response => {
      if (response.success) {
        if (this.email === 'limonislamborno@gmail.com') {
          this.router.navigate(['/dashboard1']);
        } else if (this.email === 'idbstudentbd@gmail.com') {
          this.router.navigate(['/dashboard2']);
        }
      } else {
        // Handle login error
      }
    });
  }
}
