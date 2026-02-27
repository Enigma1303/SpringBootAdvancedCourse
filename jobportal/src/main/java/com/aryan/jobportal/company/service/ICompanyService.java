package com.aryan.jobportal.company.service;

import com.aryan.jobportal.dto.CompanyDto;
import com.aryan.jobportal.entity.Company;

import java.util.List;

public interface ICompanyService {

    List<CompanyDto> getAllCompanies();

}
