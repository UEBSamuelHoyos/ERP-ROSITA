import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = ''; // Declare as string
  password: string = ''; // Declare as string
  errorMessage: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('http://localhost:8080/api/auth/login', { username: this.username, password: this.password })
      .subscribe({
        next: () => this.router.navigate(['/home']),
        error: () => this.errorMessage = 'Usuario o contraseña incorrectos'
      });
  }
}
