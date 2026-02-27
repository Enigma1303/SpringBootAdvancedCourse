package com.aryan.jobportal.company.service.impl;

import com.aryan.jobportal.company.service.ICompanyService;
import com.aryan.jobportal.constants.ApplicationConstants;
import com.aryan.jobportal.dto.CompanyDto;
import com.aryan.jobportal.dto.JobDto;
import com.aryan.jobportal.entity.Company;
import com.aryan.jobportal.entity.Job;
import com.aryan.jobportal.repository.CompanyRepository;
import com.aryan.jobportal.company.service.ICompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companyList =companyRepository.findAllWithJobsByStatusNative(ApplicationConstants.ACTIVE_STATUS);
        return companyList.stream().map(this::transformCompanyToDto).collect(Collectors.toList());
    }

    private CompanyDto transformCompanyToDto(Company company) {
        List<JobDto> jobDtos = company.getJobs().stream()
                .map(this::transformJobToDto)
                .collect(Collectors.toList());
        return new CompanyDto(company.getId(), company.getName(), company.getLogo(),
                company.getIndustry(), company.getSize(), company.getRating(),
                company.getLocations(), company.getFounded(), company.getDescription(),
                company.getEmployees(), company.getWebsite(), company.getCreatedAt(),jobDtos);
    }

    private JobDto transformJobToDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getCompany().getId(),
                job.getCompany().getName(),
                job.getCompany().getLogo(),
                job.getLocation(),
                job.getWorkType(),
                job.getJobType(),
                job.getCategory(),
                job.getExperienceLevel(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getSalaryCurrency(),
                job.getSalaryPeriod(),
                job.getDescription(),
                job.getRequirements(),
                job.getBenefits(),
                job.getPostedDate(),
                job.getApplicationDeadline(),
                job.getApplicationsCount(),
                job.getFeatured(),
                job.getUrgent(),
                job.getRemote(),
                job.getStatus()
        );
    }

}
