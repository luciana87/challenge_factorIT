import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private authService: AuthService, private router: Router) { }
  
  ngOnInit(): void {
    this.authService.getRole().subscribe({

      next: (response) => {
        if (response == "ADMIN") {
          this.router.navigate(['/admin']) 
        } else {
          this.router.navigate(['/cart/create']) 
        }
        
      },
      error: (error) => {
        console.error('Error al crear item: ', error)
      }      

    })
  }
}
