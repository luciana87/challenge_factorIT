import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/shared/home/home.component';
import { authGuard } from './guards/auth.guard';
import { CreateCartComponent } from './components/cart/create-cart/create-cart.component';
import { ProductsComponent } from './components/products/products.component';
import { GetCartComponent } from './components/cart/get-cart/get-cart.component';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate: [authGuard] },
    { path: 'cart/create', component: CreateCartComponent },
    { path: 'products', component: ProductsComponent },
    { path: 'get-cart/:cartId', component: GetCartComponent }
];