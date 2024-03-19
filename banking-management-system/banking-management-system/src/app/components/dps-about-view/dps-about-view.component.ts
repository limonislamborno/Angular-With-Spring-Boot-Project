import { Component } from '@angular/core';
import { DpsAbout } from '../../model/dps-about';
import { DpsAboutService } from '../../shared/dps-about.service';

@Component({
  selector: 'app-dps-about-view',
  templateUrl: './dps-about-view.component.html',
  styleUrl: './dps-about-view.component.css'
})
export class DpsAboutViewComponent {

  dpsList ?: DpsAbout[];

  constructor(private dpsService: DpsAboutService) { }

  ngOnInit(): void {
    this.loadDPSList();
  }

  loadDPSList(): void {
    this.dpsService.getAllDps().subscribe(data => {
      this.dpsList = data;
    });
  }

}
