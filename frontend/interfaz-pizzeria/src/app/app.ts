import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Home } from './cliente/home/home';
import { Footer } from './cliente/footer/footer';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,Footer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('interfaz-pizzeria');
}
