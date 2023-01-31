package com.gen.com.Insurance_portal.models.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamsModel {
    private String filter;
    private int pageNumber = 0;
    private int pageSize = 3;
    private String[] sort = {"id","desc"};
}
