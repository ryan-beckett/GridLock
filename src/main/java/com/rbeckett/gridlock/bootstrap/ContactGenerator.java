package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.model.business.Contact;
import com.rbeckett.gridlock.model.business.Location;
import com.rbeckett.gridlock.services.business.ContactService;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactGenerator implements Generator<Contact> {

    private static final DataFactory dataFactory = new DataFactory();
    private static final String[] JOB_TITLES = {"Financial Clerk", "Accountant", "Assistant HR Manager",
            "Chief Executive Officer", "Department Manager", "Database Administrator", "System Administrator",
            "Java Developer", "DevOps Engineer", "Marketing Director"};
    private final List<Contact> contacts = new ArrayList<>();
    private final ContactService contactService;

    public ContactGenerator(final ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void generate(final int numResults, final Generator... generators) {
        for (int i = 0; i < numResults; i++) {
            Contact contact = new Contact();
            contact.setEmail(dataFactory.getEmailAddress());
            contact.setFirstName(dataFactory.getFirstName());
            contact.setLastName(dataFactory.getLastName());
            contact.setJobTitle(JOB_TITLES[i % numResults]);
            contact.setPhone(dataFactory.getNumberText(10));
            contact.setLocation((Location) dataFactory.getItem(generators[0].getResults()));
            contacts.add(contactService.save(contact));
        }
    }

    @Override
    public List<Contact> getResults() {
        return contacts;
    }
}
