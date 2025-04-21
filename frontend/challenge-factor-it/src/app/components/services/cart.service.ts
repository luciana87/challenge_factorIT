import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Cart } from "../../models/Cart";

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
        const token = localStorage.getItem('token')
        // Si el token existe, agregarlo al encabezado de la solicitud
        const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token)
        return this.http.post(`${this.apiUrl}/create`, payload, { headers })
    }
    
    public getCart(cartId: number): Observable<Cart> {        
        return this.http.get<Cart>(`${this.apiUrl}/${cartId}`)
    }

}
