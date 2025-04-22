import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/shared/home/home.component';
import { authGuard } from './guards/auth.guard';
import { CreateCartComponent } from './components/cart/create-cart/create-cart.component';
import { GetCartComponent } from './components/cart/get-cart/get-cart.component';
import { AddItemComponent } from './components/item/add-item/add-item.component';
import { ModifyAmountComponent } from './components/cart/modify-amount/modify-amount.component';
import { AdminComponent } from './components/admin/admin.component';
import { ActiveComponent } from './components/user/active/active.component';
import { InactiveComponent } from './components/user/inactive/inactive.component';
import { VipsComponent } from './components/user/vips/vips.component';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate: [authGuard] },
    { path: 'cart/create', component: CreateCartComponent, canActivate: [authGuard] },
    { path: 'cart/:cartId/products/:productId/add', component: AddItemComponent, canActivate: [authGuard]},
    { path: 'cart/:cartId', component: GetCartComponent , canActivate: [authGuard]},
    { path: 'cart/:cartId/item/:itemId', component: ModifyAmountComponent, canActivate: [authGuard] },
    { path: 'admin', component: AdminComponent, canActivate: [authGuard] },
    { path: 'user/vips', component: VipsComponent, canActivate: [authGuard] },
    { path: 'user/vips/inactive/per_month', component: InactiveComponent, canActivate: [authGuard] },
    { path: 'user/vips/active/per_month', component: ActiveComponent, canActivate: [authGuard] }
];