package com.product.category.domain;

import com.product.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequestPayload {

    private List<Long> requestIds;       
    private Integer approvedBy;
    private RequestStatus status;
}
