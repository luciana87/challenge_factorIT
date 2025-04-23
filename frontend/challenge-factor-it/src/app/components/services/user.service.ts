import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../../models/User";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private readonly apiUrl = 'http://localhost:8080/user'

    constructor(private http: HttpClient) { }

    public getVips(): Observable<User[]> {
        return this.http.get<User[]>(`${this.apiUrl}/vip`)
    }

    public getActiveVips(active: boolean, year: number, month: number): Observable<User[]> {
        const params = new HttpParams()
            .set('active', active)
            .set('year', year)
            .set('month', month);

        return this.http.get<User[]>(`${this.apiUrl}/vip/by-month`, { params });
    }
}