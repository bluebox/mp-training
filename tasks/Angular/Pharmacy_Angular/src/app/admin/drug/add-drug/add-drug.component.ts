import { Component,Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'
import { DrugService } from 'src/app/services/drug.service';
import { DistributorService } from 'src/app/services/distributor.service';
import { ManufacturerService } from 'src/app/services/manufacturer.service';

@Component({
  selector: 'app-add-drug',
  templateUrl: './add-drug.component.html',
  styleUrls: ['./add-drug.component.css']
})
export class AddDrugComponent implements OnInit {

  drugForm !: FormGroup;
  actionBtn : string = "Save";
  dis_data:any;
  man_data:any;

  constructor( private formBuilder : FormBuilder, 
    private api : DrugService, private apid:DistributorService, private apim:ManufacturerService,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    private dialogRef : MatDialogRef<AddDrugComponent>) { }

  ngOnInit(): void {
    this.apid.getDistributor().subscribe(datad=>{
      this.dis_data=datad
    })
    this.apim.getManufacturer().subscribe(datam=>{
      this.man_data=datam
    })

    this.drugForm = this.formBuilder.group({
      drug_id : ['',Validators.required],
      drug_name : ['',Validators.required],
      dist_name : ['',Validators.required],
      company_name : ['',Validators.required],
      mrp : ['',Validators.required],
      discount : ['',Validators.required],
      stock : ['',Validators.required],
      mfg_date : ['',Validators.required]
    })

    if(this.editData){
      this.actionBtn = "Update";
      this.drugForm.controls['drug_id'].setValue(this.editData.drug_id);
      this.drugForm.controls['drug_name'].setValue(this.editData.drug_name);
      this.drugForm.controls['dist_name'].setValue(this.editData.dist_name);
      this.drugForm.controls['company_name'].setValue(this.editData.company_name);
      this.drugForm.controls['mrp'].setValue(this.editData.mrp);
      this.drugForm.controls['discount'].setValue(this.editData.discount);
      this.drugForm.controls['stock'].setValue(this.editData.stock);
      this.drugForm.controls['mfg_date'].setValue(this.editData.mfg_date);
    }
  }


  addDrug(){
    if(!this.editData){
      if(this.drugForm.valid){
        this.api.postDrug(this.drugForm.value)
        .subscribe({
          next:(res)=>{
            console.log(this.drugForm.value)
            alert("Drug Added Successfully!");
            this.drugForm.reset();
            this.dialogRef.close('save');
          },
          error:()=>{
            alert("Error Drug Not Added!")
          }
        })
      }

    }
    else{
      this.updateDrug()
      }
  }

  updateDrug(){
    this.api.putDrug(this.drugForm.value, this.editData.id)
    .subscribe({
      next:(res)=>{
        alert("Drug updated Successfully!");
        this.drugForm.reset();
        this.dialogRef.close("update");
      },
      error:()=>{
        alert("Error While updating the record!");
      }
    })
  }

}


