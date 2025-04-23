import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


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
        this.loginSuccess.emit();    
      },
      (error) => {
        Swal.fire({
          title: "¡Inicio de sesión fallido!",
          text: "Verifica tus credenciales",
          icon: "error"
        }).then(() => {
          this.username = '';
          this.password = '';
        });
      }
    )
  }
}
