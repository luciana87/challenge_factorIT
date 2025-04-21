import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


export interface Product {
    id: number,
    name: string,
    price: number,
    description: string,
    category: string,
    deleted: boolean
}

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
