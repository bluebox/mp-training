import { Component, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { elementAt, map } from 'rxjs';

@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.scss']
})
export class LoansComponent implements OnInit, OnChanges {
  addLoanTypesForm! :FormGroup;
  constructor(private activatedRoute: ActivatedRoute , private fb:FormBuilder) { }
  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes)
  }
  
  trackLoanName():any {
    // console.log("value is  changed in track");
    // this.addLoanTypesForm.valueChanges.subscribe(data=>{
    //   console.log(data);
    // })
    // this.addLoanTypesForm.get('loanType')?.valueChanges.subscribe(data=>{
    //   console.log(data);
    // })
    this.addLoanTypesForm.get('loanType')?.valueChanges.pipe(map (element=>{
      console.log(element);
    }))
  }

  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot.data)
   
    // form group
    // this.addLoanTypesForm = new FormGroup(
    //   {
    //     'loanName':new FormControl(),
    //     'loanType': new FormControl(),
    //     'loanDescription': new FormControl(),
    //   }
    // )
    // form buider
    // setting the values of direct way
    // this.addLoanTypesForm = this.fb.group(
    //   {
    //     'loanName':new FormControl("shyam"),
    //     'loanType': new FormControl("lic"),
    //     'loanDescription': new FormControl("Hello bro"),
    //   }
    // )
    // this.addLoanTypesForm = this.fb.group(
    //   {
    //     'loanName':new FormControl(),
    //     'loanType': new FormControl(),
    //     'loanDescription': new FormControl(),
    //   }
    // )
    let users=new Array(
      [  new FormControl('ABD'),
        new FormControl('123')]
      );
    this.addLoanTypesForm = this.fb.group(
      {
        'loanName':new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(20)]),
        'loanType': new FormControl('', Validators.required),
        'loanDescription': new FormControl('', Validators.compose([
          Validators.required, Validators.minLength(10), Validators.maxLength(200)
        ])),
        // 'user':new FormArray(
        // [  new FormControl('ABD'),
        //   new FormControl('123')]
        // )
        // FormArray wwith FormGroup
        'users':new FormArray([
          this.fb.group({
            'name':new FormControl(''),
            'age':new FormControl(''),
            'dept':new FormControl(''),

          })
        ])
      }
    )
    // setting the values in first way
    const newLoan={
      'loanName':"shyam",
        'loanType':"lic",
        'loanDescription':"Hello fej bro",

    }
    // this.addLoanTypesForm.setValue(newLoan)
    // setting the values in patch way 
    const newLoan1={
      'loanName':"shyam",
        'loanType':"lic",
        'loanDescription':"Hello fej bro",
        

    }
    this.addLoanTypesForm.patchValue(newLoan1)
    this.addLoanTypesForm.get("loanType")?.statusChanges.subscribe(data=>{
      console.log(data)
    })
  }
  get users(): FormArray{
    return this.addLoanTypesForm.get('users') as FormArray;
  }
  addUser(){
    let userArray=this.addLoanTypesForm.get('user') as FormArray;
    let newArray = this.fb.group({
      'name':'',
      'age':'',
      'dept':''
    });
    
    userArray.push(newArray)
  }
  removeUser(i: any){
  let arr= this.addLoanTypesForm.get('user') as FormArray;
  arr.removeAt(i)
  }
  addloantype(){
  
    // console.log(this.addLoanTypesForm.valid)
    // console.log(this.addLoanTypesForm.invalid)
    // console.log(this.addLoanTypesForm.pending)
    // console.log(this.addLoanTypesForm.pristine)
    // console.log(this.addLoanTypesForm.dirty)
    // console.log(this.addLoanTypesForm.touched)
    // console.log(this.addLoanTypesForm.untouched)
    console.log(this.addLoanTypesForm.value)
    // console.log(this.addLoanTypesForm.get('loanType').value
    // console.log(this.addLoanTypesForm.get('loanType')?.value)
    // this.addLoanTypesForm.valueChanges.pipe(map (element=>{
    //   console.log(element)
    // }))
    // this.addLoanTypesForm.valueChanges.subscribe(data=>{
    //   console.log(data)
    // })
  }
  resetForm(){
    this.addLoanTypesForm.reset()
  }
}
