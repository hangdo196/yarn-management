import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { RegisterOwnerComponent } from './register-owner/register-owner.component';

export const routes: Routes = [
    { path: 'register-User', component: RegisterComponent },
    { path: 'register-Owner', component: RegisterOwnerComponent}
];
