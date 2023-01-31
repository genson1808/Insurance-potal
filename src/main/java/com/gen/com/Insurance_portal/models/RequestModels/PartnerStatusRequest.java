package com.gen.com.Insurance_portal.models.RequestModels;

import com.gen.com.Insurance_portal.common.enums.PartnerStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerStatusRequest {

    @Schema(name = "status", type = "String", required = true, description = "value in (PENDING, APPROVED, DENIED)")
    @NotNull(message = "status is required!")
    private PartnerStatus status;
}
