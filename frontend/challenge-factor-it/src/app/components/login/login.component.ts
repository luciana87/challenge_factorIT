import { CommonModule } from '@angular/common';
import {  HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import Swal from 'sweetalert2';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  @Output() loginSuccess = new EventEmitter<void>();

  username: string = ''
  password: string = ''
  errorMessage: string = ''

  constructor(private authService: AuthService, private router: Router) { }
  onLogin () {
    this.authService.login(this.username, this.password).subscribe(
      (response) => {
        console.log('Login exitoso', response)
        localStorage.setItem('token', response.token)  
        this.router.navigate(['/home'])      
      },
      (error) => {
        console.error('Error al iniciar sesión.', error)
        this.errorMessage = 'Usuario o contraseña incorrectos'
      }
    )
  }
}
  // onLogin() {
  //   const payload = {
  //     username: this.username,
  //     password: this.password
  //   }
  //   this.http.post<any>('http://localhost:8080/auth/login', payload).subscribe({
  //     next: (response) => {
  //       const token = response.token
  //       localStorage.setItem('authToken', token)
  //       console.log('Login exitoso, token guardado.')
  //     }
  //   })
  // }
