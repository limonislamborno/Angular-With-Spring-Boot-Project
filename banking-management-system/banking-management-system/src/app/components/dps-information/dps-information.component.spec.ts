import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DpsInformationComponent } from './dps-information.component';

describe('DpsInformationComponent', () => {
  let component: DpsInformationComponent;
  let fixture: ComponentFixture<DpsInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DpsInformationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DpsInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
