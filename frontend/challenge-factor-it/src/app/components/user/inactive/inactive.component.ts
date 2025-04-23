import { Component } from '@angular/core';
import { formatDateTime } from '../../../utils/functions';
import { FormsModule } from '@angular/forms';
import { User } from '../../../models/User';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { BooleanToSiNoPipe } from '../../../pipes/boolean-to-si-no.pipe';

@Component({
  selector: 'app-inactive',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink, BooleanToSiNoPipe ],
  templateUrl: './inactive.component.html',
  styleUrl: './inactive.component.css'
})
export class InactiveComponent {

  date: string;
  vips: User[] = []

  constructor(private service: UserService) {
        const today = new Date();
        this.date = formatDateTime(today)
  }

  public search() {
    const formatDate = new Date(this.date)
    this.service.getActiveVips(false, formatDate.getFullYear(), formatDate.getMonth()+1).subscribe({
      next: (response) => {
        this.vips = response
      },
      error: (error) => {
        console.error(error)
      }
    })
  }

}
