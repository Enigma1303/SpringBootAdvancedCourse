package com.aryan.jobportal.repository;

import com.aryan.jobportal.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}