import { Component, OnInit } from '@angular/core';
import { Tag } from '../../core/tag';
import { TagService } from '../../core/tag.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tag-form',
  templateUrl: './tag-form.component.html',
  styleUrls: ['./tag-form.component.css']
})
export class TagFormComponent implements OnInit {

  tag: Tag;

  constructor(private tagService: TagService, private route: ActivatedRoute, private router: Router) {
    this.tag = {
      'label': '',
      'author': null,
      bookmarks: null
    };
  }

  ngOnInit() {

    const id = (this.route.snapshot.paramMap.get('id'));
    console.log({ id });

    if (id) {
      // console.log({id});
      this.tagService.getById(id).subscribe(
        (tag: Tag) => { this.tag = tag; }
      );
    }
  }

  onSubmit() {
    if (this.tag.id) {
      // Update
      console.log('update');
      this.tagService.update(this.tag).subscribe(
        (tag: Tag) => this.tag = tag
      );
    } else {
      // Create
      console.log('create');
      this.tagService.create(this.tag).subscribe(
        (tag: Tag) => {
          console.log('Success');
          this.tag = tag;
        },
        (error: Error) => {
          console.log('Error : ' + error.message);
        }
      );
    }
    this.router.navigateByUrl('/tags');
  }

}
