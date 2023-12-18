import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRoles(roles: {}) {
    console.log("helOo", roles);
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): [] {
    // return JSON.parse(localStorage.getItem('roles') || '{}');
    // return JSON.parse(localStorage.getItem('roles')!);

    // let getRoles: string = localStorage.getItem('roles') as string;
    // return JSON.parse(getRoles);

    const result = JSON.parse(localStorage.getItem('roles') as string);
    console.log('Dhina', result);
    return result;
  }

  public setToken(jwtToken: string) {
    localStorage.setItem("jwtToken", jwtToken);
  }

  public getToken(): string {
    return localStorage.getItem("jwtToken") as string;
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() {
    return this.getRoles() && this.getToken();
  }

}
