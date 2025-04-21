import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { BooleanToSiNoPipe } from '../../pipes/boolean-to-si-no.pipe';
import { Product } from '../../models/Product';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, BooleanToSiNoPipe],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  products: Product[] = []

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
      next: (data) => {
        console.log(data)
        this.products = data
      },
      error: (error) => {
        console.error('Error obteniendo la lista de productos.')
      }
    })
  }
}
