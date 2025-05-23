import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCartComponent } from './get-cart.component';

describe('GetCartComponent', () => {
  let component: GetCartComponent;
  let fixture: ComponentFixture<GetCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetCartComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
