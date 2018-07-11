import { Component, OnInit } from '@angular/core';
import { Author } from '../author';
import { ProfileService } from '../profile.service';
import { FormGroup, Validators, FormBuilder } from '../../../../node_modules/@angular/forms';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  author: Author = {
    firstName: '',
    lastName: ''
  };

  //  label for alert message if required fields are empty
  titleAlert = 'This field is required';

  // formGroup grouping all fields in the form
  formGroup: FormGroup;

  constructor(private profileService: ProfileService,
    private formBuilder: FormBuilder) {
    // creation of formGroup using formBuilder
    this.formGroup = formBuilder.group({
      'firstName': [this.author.firstName, Validators.required], // firstName field with default value and attached controls
      'lastName': [this.author.lastName,
      Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(25)])]
    });

  }

  ngOnInit() {
  }

  onSubmit(formValue) {
    this.author.firstName = formValue.firstName;
    this.author.lastName = formValue.lastName;
    console.log(this.author);
    this.create();
  }

  create() {
    this.profileService.create(this.author).subscribe(
      (author: Author) => {
        console.log('Creation OK');
        this.author = author;
        localStorage.profileId = author.id;
      },
      (error) => {
        console.log('Ah bah oui mais la forcément ça va marcher moins bien');
      }
    );
  }



}
