import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { RegisterService } from './register/register.service';

@Component({
  selector: 'app-root',
  imports: [RegisterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [RegisterService]
})
export class AppComponent {
  title = 'frontend';
}
