import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service'; // Adjust the path as necessary

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    if (this.authService.isLoggedIn()) {
      if (route.routeConfig?.path === 'login') {
        this.router.navigate(['/']);
        return false;
      }
      return true;
    }

    if (route.routeConfig?.path === 'form') {
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }
}
