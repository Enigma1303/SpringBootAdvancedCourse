package com.aryan.jobportal.repository;

import com.aryan.jobportal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Optional
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
