package com.sharipov.license.service.impl;

import com.sharipov.license.model.License;
import com.sharipov.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    @Override
    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextLong(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("DDSoftware");
        license.setLicenseType("full");

        return license;
    }

    @Override
    public String createLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format("This is the post and the object is: %s", license.toString());
        }
        return responseMessage;
    }

    @Override
    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format("This is the put and object is: %s", license.toString());
        }
        return responseMessage;
    }

    @Override
    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format(
                "Deleting license with id %s for the organization %s", licenseId, organizationId
        );
        return responseMessage;
    }
}
