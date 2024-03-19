import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DpsAboutViewComponent } from './dps-about-view.component';

describe('DpsAboutViewComponent', () => {
  let component: DpsAboutViewComponent;
  let fixture: ComponentFixture<DpsAboutViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DpsAboutViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DpsAboutViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
