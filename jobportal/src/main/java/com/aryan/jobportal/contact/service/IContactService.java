package com.aryan.jobportal.contact.service;

import com.aryan.jobportal.dto.ContactRequestDto;

public interface IContactService {

    boolean saveContact(ContactRequestDto contactRequestDto);

}
