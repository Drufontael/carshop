import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignmentsPdfComponent } from './consignments-pdf.component';

describe('ConsignmentsPdfComponent', () => {
  let component: ConsignmentsPdfComponent;
  let fixture: ComponentFixture<ConsignmentsPdfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsignmentsPdfComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConsignmentsPdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
