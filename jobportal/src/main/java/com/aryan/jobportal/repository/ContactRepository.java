package com.aryan.jobportal.repository;

import com.aryan.jobportal.entity.Contact;

import java.util.List;

import org.hibernate.query.Page;
import org.springdoc.core.converters.models.Pageable;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

      List<Contact> findContactsByStatus(String status);

    List<Contact> findContactsByStatusOrderByCreatedAtAsc(String status);

    List<Contact> findContactsByStatus(String status, Sort sort);

}