import {Author} from './author';
import {Tag} from './tag';

export interface Bookmark {
    id?: string;
    title: string;
    description: string;
    link: string;
    author: Author;
    tags: Tag[];
}
