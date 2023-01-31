package com.gen.com.Insurance_portal.services;

import com.gen.com.Insurance_portal.entites.Partner;
import com.gen.com.Insurance_portal.models.RequestModels.CreatePartnerModel;
import com.gen.com.Insurance_portal.models.RequestModels.PartnerStatusRequest;
import com.gen.com.Insurance_portal.models.RequestModels.UpdateProductProviderModel;

import java.util.Optional;

public interface IPartnerService extends IAbstractService<Partner> {
    void save(CreatePartnerModel partnerModel);
    void update(UpdateProductProviderModel productProviderModel, Long id);
    void toggleStatus(Long id);
    void active(Long id);
    void status(Long id, PartnerStatusRequest statusRequest);
    Optional<Partner> findByCode(String code);
}
