import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loginUrl = environment.authApiUrl;

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    const loginData = { email, password };
    return this.http.post<any>(this.loginUrl, loginData)
      .pipe(
        tap((response) => {
          if (response && response.token) {
            localStorage.setItem('jwt_token', response.token);
            // localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()));
          }
        })
      );
  }

  logout(): void {
    localStorage.removeItem('jwt_token');
    // localStorage.removeItem("expires_at");
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('jwt_token');
  }
}
