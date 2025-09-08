package com.medplus.lms.dto;

import java.util.List;
import java.util.Map;

public class AllEnumsResponse {
    private Map<String, List<EnumResponse>> enums;

    public AllEnumsResponse(Map<String, List<EnumResponse>> enums) {
        this.enums = enums;
    }

    public Map<String, List<EnumResponse>> getEnums() {
        return enums;
    }
}
