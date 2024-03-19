import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DpsAboutComponent } from './dps-about.component';

describe('DpsAboutComponent', () => {
  let component: DpsAboutComponent;
  let fixture: ComponentFixture<DpsAboutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DpsAboutComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DpsAboutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
