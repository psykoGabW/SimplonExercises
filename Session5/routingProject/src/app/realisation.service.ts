import { Injectable } from '@angular/core';
import REAL_DATA from './real-data';
import { IRealisation } from './IRealisation';

@Injectable({
  providedIn: 'root'
})
export class RealisationService {

  private myRealisations: IRealisation[] = REAL_DATA.realisations;

  constructor() { }

  public getMyRealisations(): IRealisation[] {
    return this.myRealisations;
  }

  public getRealisation(iReal: number): IRealisation {
    console.log('iReal searched: ' + iReal + ' ' + typeof(iReal)); // Attention iReal peut être de type string s'il contient un chiffre
    const real: IRealisation[] = this.myRealisations.filter((elt: IRealisation) => elt.id === iReal);
    console.log(real);

    return real[0];

  }

}
