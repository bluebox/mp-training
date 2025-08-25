package com.VIMS.VIMSBackend.Repo;

import java.util.List;

import com.VIMS.VIMSBackend.Model.InsuranceModel;

public interface InsuranceRepoInterface {
        int CreateInsurance(InsuranceModel insurance);
        int updateInsurance(InsuranceModel insurance);
        int inactiveInsurance(InsuranceModel insurance);
        InsuranceModel getInsuranceById(int InsuranceId);
        List<InsuranceModel> getAllInsurances();
}
