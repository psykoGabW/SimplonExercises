import { Component, OnInit } from '@angular/core';
import { IRealisation } from '../IRealisation';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { RealisationService } from '../realisation.service';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'app-display-realisation-details',
  templateUrl: './display-realisation-details.component.html',
  styleUrls: ['./display-realisation-details.component.css']
})
export class DisplayRealisationDetailsComponent implements OnInit {

  realisation: IRealisation = {id: 0, name: 'Unnamed', image: '', description: 'Unknown'};

  constructor(private route: ActivatedRoute, private realService: RealisationService) {
   }

  ngOnInit() {
    this.route.params
      .subscribe(params => {
        const id: string = params.id;
        const realTmp = this.realService.getRealisation(Number(id).valueOf());

        if (!isNullOrUndefined(realTmp) ) {
          this.realisation = realTmp;
        }
      });
  }

}
