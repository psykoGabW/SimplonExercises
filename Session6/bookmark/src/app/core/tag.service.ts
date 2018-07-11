import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of, from, Scheduler, throwError } from 'rxjs';
import { Tag } from './tag';
import { asap } from '../../../node_modules/rxjs/internal/scheduler/asap';


@Injectable({
  providedIn: 'root'
})
export class TagService {

  static readonly TAG_URL = 'https://bnppf1-bookmarks.herokuapp.com/tag';

  constructor(private http: HttpClient) { }

  public getById(tagId: string): Observable<Tag> {
    return this.http.get<Tag>(TagService.TAG_URL + '/' + tagId);
  }

  public getAll(): Observable<Tag[]> {
    return this.http.get<Tag[]>(TagService.TAG_URL);
  }

  public update(tag: Tag): Observable<Tag> {
    return this.http.put<Tag>(TagService.TAG_URL + '/' + tag.id, tag);
  }

  public create(tag: Tag): Observable<Tag>  {
    // Cette commande permet de créer un Tag authoringTag depuis l'objet tag passé en paramètre

    console.log(localStorage.profileId);

    if (!localStorage.profileId) {
      console.log('before launching Error');
      return throwError(new Error('profileId is unknown'), asap);
    }

    const authoringTag = {
      ...tag,
      author: localStorage.profileId || '5b4351cd31beea0014db0c37'
    };
    return this.http.post<Tag>(TagService.TAG_URL, authoringTag);
  }

  public delete(tag: Tag): Observable<any> {
    return this.http.delete(TagService.TAG_URL + '/' + tag.id);
  }
}
