package com.aryan.jobportal.contact.service.impl;

import com.aryan.jobportal.contact.service.IContactService;
import com.aryan.jobportal.dto.ContactRequestDto;
import com.aryan.jobportal.entity.Contact;
import com.aryan.jobportal.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        boolean result = false;
        Contact contact = contactRepository.save(transformToEntity(contactRequestDto));
        if(contact != null && contact.getId() != null) {
            result = true;
        }
        return result;
    }

    private Contact transformToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
//        contact.setCreatedAt(Instant.now());
//        contact.setCreatedBy("System");
        contact.setStatus("NEW");
        return contact;
    }
}
