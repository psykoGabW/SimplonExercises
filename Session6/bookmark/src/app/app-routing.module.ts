import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListComponent } from './bookmark/list/list.component';
import { DetailComponent } from './tags/detail/detail.component';
import { TagsListComponent } from './tags/tags-list/tags-list.component';
import { TagFormComponent } from './tags/tag-form/tag-form.component';
import { ProfileComponent } from './core/profile/profile.component';

const routes: Routes = [
  {path: 'bookmarks', component: ListComponent },
  {path: 'tags', component: TagsListComponent},
  {path: 'tag/create', component: TagFormComponent},
  {path: 'tag/:id', component: DetailComponent},
  {path: 'tag/form/:id', component: TagFormComponent},
  {path: 'profile', component: ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
