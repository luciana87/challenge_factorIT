import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private apiUrl = 'http://localhost:8080/auth'

    constructor(private http: HttpClient, private router: Router) { }

    getLoggedIn(): boolean {
        const token = localStorage.getItem('token')
        return token ? true : false;
    }


    login(username: string, password: string): Observable<any> {
        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json');

        const body = { username, password };

        return this.http.post(`${this.apiUrl}/login`, body, { headers });
    }

    logout() {
        localStorage.removeItem('token')
        this.router.navigate(['/login'])
    }
    
    getRole(): Observable<String> {
        return this.http.get<String>(`${this.apiUrl}/role`)
    }
}
