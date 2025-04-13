import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RegisterService } from './register.service';

@Component({
  selector: 'register',
  imports: [CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerMessage: string | undefined;

  constructor(private registerService: RegisterService) {}

  registerUser() {
    this.registerService.register().subscribe({
      next: response => {
        this.registerMessage = response;
      },
      error: error => {
        this.registerMessage = 'Error during registration: ' + error.message;
      },
      complete: () => {
        console.log('Registration process completed.');
      }
    });
  }
}
