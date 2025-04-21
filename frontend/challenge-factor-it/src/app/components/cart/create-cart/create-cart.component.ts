import { Component } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { formatDateTime } from '../../../utils/functions';

@Component({
  selector: 'app-create-cart',
  standalone: true,
  imports: [],
  templateUrl: './create-cart.component.html',
  styleUrl: './create-cart.component.css'
})
export class CreateCartComponent {

  constructor(private cartService: CartService){}

  createCart() {
    const createdAt = formatDateTime(new Date())
    console.log(createdAt)

    this.cartService.createCart({ created_at: createdAt}).subscribe({
      next: (response) => {
        console.log('Carrito creado: ', response)
        //Redirigir a la lista de productos supongo
        
      },
      error: (error) => {
        console.error('Error al crear cart: ', error)
      }
    })    
  }
}
