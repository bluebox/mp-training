package com.VIMS.VIMSBackend.Model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsuranceModel {
   @NotNull(message="Id must not be null value")	
   private int InsuranceId;
   @NotNull(message="Selection must not be Null and Number")
   private int PeriodLength;
   @NotNull(message="Please add valid Amount")
   private Double InsuranceAmount;
   @NotNull(message="Selection must not be empty")
   private VehicleType VehicleType;
   @NotNull(message="Please add Valid EMI Details")
   private Double MonthlyEMI;
   @NotNull(message="Please Provide Staus of Insurance")
   private InsuranceStatus InsuranceStatus;
   @NotNull(message="Period Must be In Years and Not Null")
   private int ValidPeriod;
   @NotNull(message="Add No Claim Bonus percentage")
   private int NCB;
   private int ModifiedBy;
   @DateTimeFormat(pattern="yyyy-mm-dd")
   private Date ModifiedDate;
   @NotNull(message="Should not be Null")
   private int CreatedBy;
   @NotNull(message="Should not be Null")
   @DateTimeFormat(pattern="yyyy-mm-dd")
   private Date CreatedDate;
   
}
