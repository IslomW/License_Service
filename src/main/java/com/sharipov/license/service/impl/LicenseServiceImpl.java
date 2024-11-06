package com.sharipov.license.service.impl;

import com.sharipov.license.config.ServiceConfig;
import com.sharipov.license.model.License;
import com.sharipov.license.model.Organization;
import com.sharipov.license.repository.LicenseRepository;
import com.sharipov.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final MessageSource message;
    private LicenseRepository licenseRepository;
    ServiceConfig config;

    @Override
    public License getLicense(String licenseId, String organizationId, String clientType) {
        License license = licenseRepository
                .findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (null == license) {
            throw new IllegalArgumentException(
                    String.format(message.getMessage(
                            "license.search.error.message", null, null
                    ), licenseId, organizationId));
        }
        Organization organization = retrieveOrganizationInfo(organizationId, clientType);

        if (null != organization){
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }
        return license.withComment(config.getProperty());
    }

    @Override
    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(message.getMessage("license.create.message", null, locale),
                    license.toString());
        }
        return responseMessage;
    }

    @Override
    public String updateLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(message.getMessage("license.update.message", null, locale),
                    license.toString());
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


    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        Organization organization = null;

//        switch (clientType) {
//            case "feign":
//                System.out.println("I am using the feign client");
//                organization = organizationFeignClient.getOrganization(organizationId);
//                break;
//            case "rest":
//                System.out.println("I am using the rest client");
//                organization = organizationRestClient.getOrganization(organizationId);
//                break;
//            case "discovery":
//                System.out.println("I am using the discovery client");
//                organization = organizationDiscoveryClient.getOrganization(organizationId);
//                break;
//            default:
//                organization = organizationRestClient.getOrganization(organizationId);
//                break;
//        }

        return organization;
    }
}
