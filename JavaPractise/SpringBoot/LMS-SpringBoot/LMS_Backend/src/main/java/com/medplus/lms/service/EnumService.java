package com.medplus.lms.service;

import com.medplus.lms.domain.EnumDto;
import com.medplus.lms.domain.AvailabilityStatus;
import com.medplus.lms.domain.Category;
import com.medplus.lms.domain.Gender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EnumService {
    public Map<String, List<EnumDto>> getAllEnums() {
        Map<String, List<EnumDto>> enumMap = new HashMap<>();
        List<EnumDto> availabilityStatusList = List.of(AvailabilityStatus.values())
                .stream()
                .map(status -> new EnumDto(status.name(),status.getCode(), status.getDescription()))
                .collect(Collectors.toList());
        enumMap.put("AvailabilityStatus", availabilityStatusList);

        
        List<EnumDto> categoryList = List.of(Category.values())
                .stream()
                .map(category -> new EnumDto(category.name(),category.getCode(), category.getDescription()))
                .collect(Collectors.toList());
        enumMap.put("Category", categoryList);

       
        List<EnumDto> genderList = List.of(Gender.values())
                .stream()
                .map(gender -> new EnumDto(gender.name(),gender.getCode(), gender.getDescription()))
                .collect(Collectors.toList());
        enumMap.put("Gender", genderList);

        return enumMap;
    }
}
