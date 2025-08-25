package com.VIMS.VIMSBackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VIMS.VIMSBackend.Model.InsuranceModel;
import com.VIMS.VIMSBackend.Repo.InsuranceRepo;

@Service
public class InsuranceServiceImplementation implements InsuranceService{

	@Autowired
	private InsuranceRepo insurancerepo;
	
	
	@Override
	public int CreateInsurance(InsuranceModel insurance) {
		int value=insurancerepo.CreateInsurance(insurance);
		return value;
	}

	@Override
	public int updateInsurance(InsuranceModel insurance) {
		int value=insurancerepo.updateInsurance(insurance);
		return value;
	}

	@Override
	public int inactiveInsurance(InsuranceModel insurance) {
		int value=insurancerepo.inactiveInsurance(insurance);
		return value;
	}

	@Override
	public InsuranceModel getInsuranceById(int InsuranceId) {
		InsuranceModel value=insurancerepo.getInsuranceById(InsuranceId);
		return value;
	}

	@Override
	public List<InsuranceModel> getAllInsurances() {
		return insurancerepo.getAllInsurances();
	}

}
