package com.gen.com.Insurance_portal.models.responseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObjectModel {
    private Boolean status;
    private Object data;
}
