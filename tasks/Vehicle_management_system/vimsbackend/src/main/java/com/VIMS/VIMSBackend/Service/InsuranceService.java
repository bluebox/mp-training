package com.VIMS.VIMSBackend.Service;

import java.util.List;

import com.VIMS.VIMSBackend.Model.InsuranceModel;

public interface InsuranceService {
	int CreateInsurance(InsuranceModel insurance);
	int updateInsurance(InsuranceModel insurance);
	int inactiveInsurance(InsuranceModel insurance);
	InsuranceModel getInsuranceById(int InsuranceId);
	List<InsuranceModel> getAllInsurances();
}
