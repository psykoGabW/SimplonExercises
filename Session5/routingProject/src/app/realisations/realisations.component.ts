import { Component, OnInit } from '@angular/core';
import { RealisationService } from '../realisation.service';
import { IRealisation } from '../IRealisation';

@Component({
  selector: 'app-realisations',
  templateUrl: './realisations.component.html',
  styleUrls: ['./realisations.component.css']
})
export class RealisationsComponent implements OnInit {

  realisations: IRealisation[];

  constructor(private realService: RealisationService) {
   }

  ngOnInit() {
    this.realisations = this.realService.getMyRealisations();
  }

}
