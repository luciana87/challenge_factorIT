import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private apiUrl = 'http://localhost:8080/auth/login'

    constructor(private http: HttpClient) { }

    login(username: string, password: string): Observable<any> {
        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json');

        const body = { username, password };

        return this.http.post(this.apiUrl, body, { headers });
    }
}
