import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bookmark } from './bookmark';

@Injectable({
  providedIn: 'root'
})
export class BookmarkService {

  constructor(private http: HttpClient) { }

  public getAll(): Observable<Bookmark[]> {
    return this.http.get<Bookmark[]>('https://bnppf1-bookmarks.herokuapp.com/bookmark') ;
  }
}
