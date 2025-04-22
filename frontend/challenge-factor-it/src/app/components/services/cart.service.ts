import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Cart } from "../../models/Cart";
import { CartAddItemRequest } from "../../models/CartAddItemRequest";
import { ModifyAmountItem } from "../../models/ModifyAmountItem";

export interface CartRequest {
    created_at: string
}

@Injectable({
    providedIn: 'root'
})

export class CartService {
    private readonly apiUrl = 'http://localhost:8080/cart'

    constructor(private http: HttpClient) {}
    
    createCart(payload: CartRequest): Observable<any> {
        return this.http.post(`${this.apiUrl}/create`, payload)
    }
    
    public getCart(cartId: number): Observable<Cart> {        
        return this.http.get<Cart>(`${this.apiUrl}/${cartId}`)
    }

    public addItemToCart(cartId: number, payload: CartAddItemRequest): Observable<any> {
        return this.http.post(`${this.apiUrl}/${cartId}/item`, payload)
    }

    public modifyAmountItem(cartId: number, itemId: number, payload: ModifyAmountItem): Observable<any> {
        return this.http.put(`${this.apiUrl}/${cartId}/item/${itemId}`, payload)
    }

    public deleteItem(cartId: number, itemId: number): Observable<any> {
        return this.http.delete(`${this.apiUrl}/${cartId}/item/${itemId}`)
    }

    public checkout(cartId: number): Observable<any> {
        return this.http.put(`${this.apiUrl}/${cartId}/checkout`, null)
    }

    public deleteCart(cartId: number): Observable<any> {
        return this.http.delete(`${this.apiUrl}/${cartId}`)
    }

}
