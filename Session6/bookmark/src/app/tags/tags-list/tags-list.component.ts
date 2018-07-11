import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Tag } from '../../core/tag';
import { TagService } from '../../core/tag.service';
import { finalize } from '../../../../node_modules/rxjs/operators';

@Component({
  selector: 'app-tags-list',
  templateUrl: './tags-list.component.html',
  styleUrls: ['./tags-list.component.css']
})
export class TagsListComponent implements OnInit {

  tags$: Observable<Tag[]>;

  constructor(private tagService: TagService) { }

  ngOnInit() {
    this.refresh();
    }

  delete(tag: Tag) {
    this.tagService.delete(tag)
    .pipe(
      finalize(() => this.refresh()))
    .subscribe(
      () => console.log('Succes !'),
      () => console.log('Error'),
    );
  }

  refresh() {
    console.log('refresh');
    this.tags$ = this.tagService.getAll();
  }

}
