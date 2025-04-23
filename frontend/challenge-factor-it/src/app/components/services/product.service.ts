import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Product } from "../../models/Product";

@Injectable({
    providedIn: 'root'
})

export class ProductService {
    private readonly apiUrl = 'http://localhost:8080/products'

    constructor(private http: HttpClient) {}
    
    public getProducts(): Observable<Product[]> {
        const headers = { 'Content-Type': 'application/json' }
        return this.http.get<Product[]>(this.apiUrl, { headers })
    }
}
