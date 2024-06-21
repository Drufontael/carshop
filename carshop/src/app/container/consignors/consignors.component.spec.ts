import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignorsComponent } from './consignors.component';

describe('ConsignorsComponent', () => {
  let component: ConsignorsComponent;
  let fixture: ComponentFixture<ConsignorsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsignorsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConsignorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
