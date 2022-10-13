import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ServiceService {



  url: string = 'http://localhost:8000/'
  constructor(private http: HttpClient) { }

  getConfig() {
    return this.http.get(this.url);
  }

  freelancerRegistration(data: any) {
    console.log(data);
    return this.http.post(this.url + 'freelancer', data)
  }
  FreelancerLogin(data: string) {
    console.log(data);

    return this.http.post(this.url + "freelancer/login/",data)
  }
  freelancerDetails(data : any){
    console.log(data);
    console.log('service');
    let headers = new HttpHeaders().set('Authorization','Token '+ data.substring(1, data.length-1))
    return this.http.get(this.url + "freelancer/details/",{'headers':headers});
  }

  ClientRegistration(data: any) {
    console.log(data);

    return this.http.post(this.url + 'client', data)
  }

  ClientLogin(data: string) {
    return this.http.get(this.url + 'client/' + data + '/')
  }

  getClientJobsUrl() {
    return this.http.get(this.url + 'client_jobs')
  }

  postAJob(data: any) {
    return this.http.post(this.url + 'client_jobs', data)
  }

  submitSendProposalUrl(data: any) {
    return this.http.post(this.url + 'freelancer_proposals', data)
  }

  getFreelancerProposals(data: any) {
    return this.http.get(this.url + 'get_freelancer_proposals?id=' + data)
  }

  getJobsOfClientIdUrl(data: any) {
    return this.http.get(this.url + 'get_client_jobs?client_id=' + data)
  }

  getProposalDetails(data: any) {
    return this.http.get(this.url + 'get_proposal_details?job_id=' + data);
  }

  createContract(value: any) {
    return this.http.post(this.url + 'create_contract', value);

  }
  getContractOfClient(data: any) {
    return this.http.get(this.url + 'get_contract_of_client?client_id=' + data)
  }

  getContractOfFreelancer(data: any) { 
    return this.http.get(this.url + 'get_contract_of_freelancer?emp_proposal_id=' + data); 
  }



  clientPayment(data: any) {
    console.log(data);
    
    return this.http.post(this.url + 'client_payment', data) 
  }

  freelancerpayment(data: any) {
    return this.http.get(this.url + 'freelancer_payment?proprosal_id='+data)
  }

  getFeePaymentDetails(data : any) {
    return this.http.get(this.url + 'get_fee_payment_details?contract_id=' + data);     
  }
  getPaymentDetails(data : any)
  { 
    return this.http.get(this.url + 'get_freelancer_payment_details?freelancer_id=' + data); 
  }


  updatefreelanceproposal(proprosal_id: number ,data: any){
    console.log(data);
    
    return this.http.put(this.url + 'update_freelance_proposal' + '/'  + proprosal_id   , data);
  }

  updateContractDetails(contract_id: any ,data: any){
    return this.http.put(this.url + 'update_contract_details' + '/' + contract_id , data);
  }

  newFeedback(data : any){
    return this.http.post(this.url + 'new_feedback', data);
  }

  getFeedback(data : any){
    return this.http.get(this.url + 'freelancer_feedback?contract_id='+ data);
  }

  newfreelancerpaymentresult(data : any){
    return this.http.post(this.url + 'new_freelancer_payment', data)
  }

  clientFeedback(data : any) {
    return this.http.post(this.url + 'new_client_feedback', data)
  }

  getclientFeedback(data : any) {
    return this.http.get(this.url + 'new_client_feedback?contract_id=' + data)
  } 

}
