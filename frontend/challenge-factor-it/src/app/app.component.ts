import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './components/services/auth.service';
import { CommonModule } from '@angular/common';
import { MainComponent } from './components/shared/main/main.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ CommonModule, LoginComponent, MainComponent ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'challenge-factor-it';
  onLogin: boolean = false ;

  constructor(private authService: AuthService,  private router: Router) {  }

  ngOnInit() {
  }

  public onLogout () {
    localStorage.removeItem('token')
    this.onLogin = false;  
    this.router.navigate(['/main']) 
  }

  onLoginSuccess() {
    this.onLogin = true; 
    this.router.navigate(['/home'])    
  }
}
