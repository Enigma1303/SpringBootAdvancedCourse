package com.aryan.jobportal.company.service;

import com.aryan.jobportal.dto.CompanyDto;
import com.aryan.jobportal.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<CompanyDto> getAllCompanies();

    List<CompanyDto> getAllCompaniesForAdmin();

    void deleteCompanyById(Long id);

    boolean updateCompanyDetails(Long id, CompanyDto companyDto);

    boolean createCompany(CompanyDto companyDto);

}