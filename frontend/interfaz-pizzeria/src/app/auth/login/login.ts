import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [RouterModule,FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  email: string='';
  password: string='';
  
  onSubmit(){
    console.log('Correo:', this.email);
    console.log('Contraseña:',this.password);
  }
}
