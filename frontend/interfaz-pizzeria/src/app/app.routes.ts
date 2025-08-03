import { Routes } from '@angular/router';
import { Home } from './cliente/home/home';
import { Carrito } from './cliente/carrito/carrito';
import { Pedido } from './cliente/pedido/pedido';
import { Login } from './auth/login/login';

export const routes: Routes = [
    {path:'',component:Home},
    {path:'carrito',component:Carrito},
    {path:'pedido',component:Pedido},
    {path:'login',component:Login},
    {path:'**',redirectTo:''}
];
