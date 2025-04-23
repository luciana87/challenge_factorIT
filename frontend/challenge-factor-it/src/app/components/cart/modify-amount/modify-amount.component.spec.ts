import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyAmountComponent } from './modify-amount.component';

describe('ModifyAmountComponent', () => {
  let component: ModifyAmountComponent;
  let fixture: ComponentFixture<ModifyAmountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifyAmountComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModifyAmountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
