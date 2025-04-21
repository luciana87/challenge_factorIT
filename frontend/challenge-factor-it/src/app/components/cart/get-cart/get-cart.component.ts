import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { Item } from '../../../models/Item';
import { Cart } from '../../../models/Cart';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './get-cart.component.html',
  styleUrl: './get-cart.component.css'
})
export class GetCartComponent {

  cart: Cart = {
    id: 0,
    total: 0,
    totalProducts: 0,
    deleted: false,
    confirmed: false,
    itemsDTO: []
  }

  itemDetailCart: Item[] = [];
  cartId: number = 0


  constructor(private cartService: CartService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((param: ParamMap) => {
      const idString = param.get('cartId')
      if (idString) {
        this.cartId = +idString; //Convierte de cadena a numero
        this.cartService.getCart(this.cartId).subscribe(
          (response) => {
            this.cart = response
            this.itemDetailCart = response.itemsDTO
            console.log(this.cart)
            console.log(this.itemDetailCart)            
          })
      }
    })
  }
}
