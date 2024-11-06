package com.sharipov.license.service;

import com.sharipov.license.model.License;

import java.util.Locale;

public interface LicenseService {
    License getLicense(String licenseId, String organizationId, String clientType);
    String createLicense(License license, String organizationId, Locale locale);
    String updateLicense(License license, String organizationId, Locale locale);
    String deleteLicense(String licenseId, String organizationId);
}
