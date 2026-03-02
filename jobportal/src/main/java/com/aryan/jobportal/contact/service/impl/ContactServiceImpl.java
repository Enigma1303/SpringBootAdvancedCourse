package com.aryan.jobportal.contact.service.impl;

import com.aryan.jobportal.constants.ApplicationConstants;
import com.aryan.jobportal.contact.service.IContactService;
import com.aryan.jobportal.dto.ContactRequestDto;
import com.aryan.jobportal.dto.ContactResponseDto;
import com.aryan.jobportal.entity.Contact;
import com.aryan.jobportal.repository.ContactRepository;
import com.aryan.jobportal.util.ApplicationUtility;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    @Override
    @Transactional
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        Contact contact = contactRepository.save(transformToEntity(contactRequestDto));
        return contact != null && contact.getId() != null;
    }

    @Override
    public List<ContactResponseDto> fetchNewContactMsgs() {
        List<Contact> contacts =
                contactRepository.findContactsByStatusOrderByCreatedAtAsc(
                        ApplicationConstants.NEW_MESSAGE
                );

        return contacts.stream()
                .map(this::transformToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContactResponseDto> fetchNewContactMsgsWithSort(String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        List<Contact> contacts =
                contactRepository.findContactsByStatus(
                        ApplicationConstants.NEW_MESSAGE,
                        sort
                );

        return contacts.stream()
                .map(this::transformToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ContactResponseDto> fetchNewContactMsgsWithPaginationAndSort(
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Contact> contactPage =
                contactRepository.findContactsByStatus(
                        ApplicationConstants.NEW_MESSAGE,
                        pageable
                );

        return contactPage.map(this::transformToDto);
    }
     @Transactional
    @Override
    public boolean closeContactMsg(Long id, String status) {
        // 1 - Update Status
        // 2 - Insert in another table
        // 3 - To delete the record
        int updatedRows = contactRepository.updateStatusById(status, id, ApplicationUtility.getLoggedInUser());
        return updatedRows > 0;
    }


    private Contact transformToEntity(ContactRequestDto dto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(dto, contact);
        contact.setStatus(ApplicationConstants.NEW_MESSAGE);
        return contact;
    }

    private ContactResponseDto transformToDto(Contact contact) {
        return new ContactResponseDto(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getUserType(),
                contact.getSubject(),
                contact.getMessage(),
                contact.getStatus(),
                contact.getCreatedAt()
        );
    }
}