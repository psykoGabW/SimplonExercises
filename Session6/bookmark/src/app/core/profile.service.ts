import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { Author } from './author';
import { HttpClient } from '../../../node_modules/@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  static readonly API_URL = 'https://bnppf1-bookmarks.herokuapp.com/author/';

  constructor(private http: HttpClient) { }

  public getById(id: string): Observable<Author> {
    return this.http.get<Author>(ProfileService.API_URL + id);
  }

  public create(author: Author): Observable<Author>  {
    return this.http.post<Author>(ProfileService.API_URL, author);
  }

}
