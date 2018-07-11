import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Tag } from '../../core/tag';
import { TagService } from '../../core/tag.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private tagService: TagService) { }

  tag: Tag;

  ngOnInit() {
    this.tagService.getById(this.route.snapshot.params.id).subscribe(
      (t) => {this.tag = t; }
    );
    /*
    this.route.params.subscribe(
      (p) => {
        this.tagService.getById(p.id).subscribe(
          (t) => {this.tag = t;}
        );
        }
      );
      */
  }

}
