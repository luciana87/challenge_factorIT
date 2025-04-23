import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";



@Injectable({
    providedIn: 'root'
})
export class ItemService {
    private readonly apiUrl = 'http://localhost:8080/item'

    constructor(private http: HttpClient) { }

    public getItem (itemId: number): Observable <any> {
        return this.http.get(`${this.apiUrl}/${itemId}`)
    }

}