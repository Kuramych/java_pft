package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhonesTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new contactData().withFirstname("test1").withLastname("test2"));
        }
    }
    @Test
    public void testContactPhonesAddressEmails() {
        app.goTo().contactPage();
        contactData contact = app.contact().all().iterator().next();
        contactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));

    }

    private String mergePhones(contactData contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhonesTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
    private String mergeEmails(contactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail1(), contact.getEmail2())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
    private String mergeAddress(contactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "")
                .replaceAll("[-()]","");
    }
}
