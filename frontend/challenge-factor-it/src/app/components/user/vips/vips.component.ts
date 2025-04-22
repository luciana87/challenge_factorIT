import { Component, NgModule } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../../models/User';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-vips',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './vips.component.html',
  styleUrl: './vips.component.css'
})
export class VipsComponent {

  vips: User[] = []

  constructor(private service: UserService) {}

  ngOnInit(): void {
    this.service.getVips().subscribe({
      next: (response) => {
        this.vips = response
      },
      error: (error) => {
        console.error(error)
      }
    })
  }
}
