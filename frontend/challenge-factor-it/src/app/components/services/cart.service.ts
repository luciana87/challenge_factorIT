import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


export interface CartRequest {
    created_at: string
}

@Injectable({
    providedIn: 'root'
})

export class CartService {
    private apiUrl = 'http://localhost:8080/cart/create'

    constructor(private http: HttpClient) {}
    
    createCart(payload: CartRequest): Observable<any> {
        const token = localStorage.getItem('token')

        // Si el token existe, agregarlo al encabezado de la solicitud
        const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token)

        console.log("Authorization header: ", headers.get('Authorization'));
        return this.http.post(this.apiUrl, payload, { headers })
    }
}
