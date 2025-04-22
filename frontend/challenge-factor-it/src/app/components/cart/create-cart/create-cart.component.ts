import { Component } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { formatDateTime } from '../../../utils/functions';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-cart',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-cart.component.html',
  styleUrl: './create-cart.component.css'
})
export class CreateCartComponent {

  createdAt: string;

  constructor(private cartService: CartService, private router: Router){
    const today = new Date();
    this.createdAt = formatDateTime(today);
  }

  createCart() {
    this.cartService.createCart({ created_at: formatDateTime(new Date(this.createdAt))}).subscribe({
      next: (response) => {
        console.log('Carrito creado: ', response) 
        this.router.navigate([`/cart/${response.cartId}`]) 
        
      },
      error: (error) => {
        console.error('Error al crear cart: ', error)
      }
    })    
  }
}
