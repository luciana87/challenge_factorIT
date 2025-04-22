import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modify-amount',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './modify-amount.component.html',
  styleUrl: './modify-amount.component.css'
})


export class ModifyAmountComponent {
amount: number = 1
  itemId: number = 0
  cartId: number = 0

  constructor(private router: Router, private route: ActivatedRoute, private cartService: CartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((param: ParamMap) => {
      const cartIdString = param.get('cartId')
      const itemIdString = param.get('itemId') 
      console.log(cartIdString, itemIdString)

      if(cartIdString) {
        this.cartId = +cartIdString;
      } else {
        this.onCancel()
      }

      if (itemIdString) {
        this.itemId = +itemIdString;
      } else {
        this.onCancel()
      }

    })
  }

  public onSubmit(){
    this.cartService.modifyAmountItem(this.cartId, this.itemId, { amount: this.amount}).subscribe({
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
