import { Bookmark } from './bookmark';
import { Author } from './author';

export interface Tag {
    id?: string;
    label: string;
    author: Author; // | string;
    bookmarks: Bookmark[];
}
