package com.sms.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.Dao.Interfaces.PurchaseDao;
import com.sms.models.PurchaseDetails;
import com.sms.models.PurchaseHeader;
import com.sms.service.Interfaces.PurchaseServices;
import com.sms.utilities.Validations;

@Service
public class PurchaseServiceImplementation implements PurchaseServices {

    @Autowired
	public PurchaseDao psd;
    
   // private  static  Validations vds;


	


	@Override
    public PurchaseHeader createPurchase(PurchaseHeader ph) {
    	Validations.purchaseValidation(ph);
        return psd.createPurchase(ph);
    }

    @Override
    public List<PurchaseHeader> viewAllPurchases() {
        return psd.viewAllPurchases();
    }

    @Override
    public List<PurchaseDetails> viewPurchaseInDetail(Long purchaseId) {
        return psd.viewPurchaseInDetail(purchaseId);
    }
}
