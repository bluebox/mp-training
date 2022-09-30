import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

    return this.http.get(this.url + "freelancer/" + data + '/')
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

  getContractOfFreelancer(data: any) { return this.http.get(this.url + 'get_contract_of_freelancer' + data); }



  clientPayment(data: any) {
    return this.http.post(this.url + 'client_payment', data) 
  }

  getFeePaymentDetails(data : any) {
    return this.http.get(this.url + 'get_fee_payment_details?contract_id=' + data);     
  }

}
