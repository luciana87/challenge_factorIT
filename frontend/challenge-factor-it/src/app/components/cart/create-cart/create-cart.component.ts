import { Component } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { formatDateTime } from '../../../utils/functions';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-cart',
  standalone: true,
  imports: [],
  templateUrl: './create-cart.component.html',
  styleUrl: './create-cart.component.css'
})
export class CreateCartComponent {

  constructor(private cartService: CartService, private router: Router){}

  createCart() {
    const createdAt = formatDateTime(new Date())
    console.log(createdAt)

    this.cartService.createCart({ created_at: createdAt}).subscribe({
      next: (response) => {
        console.log('Carrito creado: ', response)
        //Redirigir a la lista de productos supongo
        this.router.navigate(['/products']) 
        
      },
      error: (error) => {
        console.error('Error al crear cart: ', error)
      }
    })    
  }
}
