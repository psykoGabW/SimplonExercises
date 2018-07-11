import { Component, OnInit } from '@angular/core';
import { BookmarkService } from '../../core/bookmark.service';
import { Bookmark } from '../../core/bookmark';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  bookmarks: Array<Bookmark> = [];
  constructor(private bookmarkService: BookmarkService) { }

  ngOnInit() {
    this.bookmarkService.getAll().subscribe(
      (list) => {
        this.bookmarks = list;  },
      (error) => console.log(error)
    );
  }

}
