package com.sharipov.license.service;

import com.sharipov.license.model.License;

public interface LicenseService {
    License getLicense(String licenseId, String organizationId);
    String createLicense(License license, String organizationId);
    String updateLicense(License license, String organizationId);
    String deleteLicense(String licenseId, String organizationId);
}
