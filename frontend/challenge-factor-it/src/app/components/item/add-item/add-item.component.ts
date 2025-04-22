
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-add-item',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-item.component.html',
  styleUrl: './add-item.component.css'
})
export class AddItemComponent {
  cantidad: number = 0
  productId: number = 0
  cartId: number = 0

  constructor(private router: Router, private route: ActivatedRoute, private cartService: CartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((param: ParamMap) => {
      const cartIdString = param.get('cartId')
      const productIdString = param.get('productId') 
      console.log(cartIdString, productIdString)

      if(cartIdString) {
        this.cartId = +cartIdString;
      } else {
        this.onCancel()
      }

      if (productIdString) {
        this.productId = +productIdString;
      } else {
        this.onCancel()
      }

    })
  }

  public onSubmit(){
    this.cartService.addItemToCart(this.cartId,{ product_id: this.productId, amount: this.cantidad}).subscribe({
      next: (response) => {
        console.log('Item creado: ', response) 
        this.router.navigate([`/cart/${this.cartId}`]) 
        
      },
      error: (error) => {
        console.error('Error al crear item: ', error)
      }
    }) 
    }

  public onCancel() {
      this.router.navigate([`/cart/${this.cartId}`]);
    }
}
