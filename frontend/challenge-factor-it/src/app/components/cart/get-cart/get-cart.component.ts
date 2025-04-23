import { Component } from '@angular/core';
import { ActivatedRoute, ParamMap, Router, RouterModule } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { Item } from '../../../models/Item';
import { Cart } from '../../../models/Cart';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { ProductService } from '../../services/product.service';
import { Product } from '../../../models/Product';
import Swal from 'sweetalert2';
import { BooleanToSiNoPipe } from '../../../pipes/boolean-to-si-no.pipe';

@Component({
  selector: 'app-get-cart',
  standalone: true,
  imports: [CommonModule, RouterModule, CurrencyPipe, BooleanToSiNoPipe],
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
  cartId: number = 0;
  products: Product[] = [];


  constructor(private productService: ProductService, private cartService: CartService, private route: ActivatedRoute, private router: Router) { }

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
            this.setProducts();
          })
      }
    })
  }


  setProducts() {
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data.filter(product =>
          !this.itemDetailCart.some(item => item.productId === product.id)
        );
      },
      error: (error) => {
        console.error('Error obteniendo la lista de productos.')
      }
    });
  }

  public delete(item: Item) {
    Swal.fire({

      text: `¿Está seguro que desea eliminar el producto ${item.productName}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.cartService.deleteItem(this.cartId, item.id).subscribe(
          (data) => {
            console.log(data);
            Swal.fire({
              position: "center",
              icon: "success",
              title: "El producto ha sido eliminado.",
              showConfirmButton: false,
              timer: 900
            });
            let indice = this.itemDetailCart.indexOf(item);
            this.itemDetailCart.splice(indice, 1);
            this.setProducts();
          });
      };
    })
  }; 


  

  public confirmarCompra() {
    Swal.fire({
      text: `¿Está seguro que desea confirmar la compra?`,
      icon: 'success',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.cartService.checkout(this.cartId).subscribe(
          (data) => {
            console.log(data);
            Swal.fire({
              position: "center",
              icon: "success",
              title: "Compra exitosa!.",
              showConfirmButton: false,
              timer: 900
            });
            
            this.router.navigate([`/home`]);
          });
      };
    })
  }; 


  

  public eliminarCarrito() {
    Swal.fire({
      text: `¿Está seguro que desea eliminar el carrito?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.cartService.deleteCart(this.cartId).subscribe(
          (data) => {
            Swal.fire({
              position: "center",
              icon: "success",
              title: "El carrito ha sido eliminado.",
              showConfirmButton: false,
              timer: 900
            });
            
            this.router.navigate([`/home`]);
          });
      };
    })
  };

}
