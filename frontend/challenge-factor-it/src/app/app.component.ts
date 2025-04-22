import { Component, Input } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthService } from './components/services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'challenge-factor-it';

  constructor(private authService: AuthService,  private router: Router) {  }

  ngOnInit() {
  }

  public onLogout () {
    localStorage.removeItem('token')
    this.router.navigate(['/login'])
  }
}
