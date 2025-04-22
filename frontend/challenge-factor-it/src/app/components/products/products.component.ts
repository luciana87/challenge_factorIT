import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { BooleanToSiNoPipe } from '../../pipes/boolean-to-si-no.pipe';
import { Product } from '../../models/Product';
import { ProductService } from '../services/product.service';
import { ActivatedRoute, ParamMap, Route, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, BooleanToSiNoPipe, RouterModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  products: Product[] = []
  cartId: number = 0

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((param: ParamMap) => {
      const idString = param.get('cartId')
      console.log(idString); 
      if (idString) {
        this.cartId = +idString; //Convierte de cadena a numero
        this.productService.getProducts().subscribe({
          next: (data) => {
            console.log(data)
            this.products = data
          },
          error: (error) => {
            console.error('Error obteniendo la lista de productos.')
          }
      })        
      } else {
        console.log("error falta parametro"); // mostrar error y redireccionar
        
      }
    })
      
      // this.productService.getProducts().subscribe({
      //   next: (data) => {
      //     console.log(data)
      //     this.products = data
      //   },
      //   error: (error) => {
      //     console.error('Error obteniendo la lista de productos.')
      //   }
      // })
    }
}
