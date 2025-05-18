import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    const datos = { username: this.username, password: this.password };
    this.http.post<any>('http://localhost:8080/login', datos).subscribe(
      res => {
        localStorage.setItem('usuario', res.username);
        localStorage.setItem('rol', res.rol);
        this.router.navigate(['/home']);
      },
      err => alert('Usuario o contraseña incorrectos')
    );
  }
}
