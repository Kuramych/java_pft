package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

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
    public void testContactPhones() {
        app.goTo().contactPage();
        contactData contact = app.contact().all().iterator().next();
        contactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getHome(), equalTo(cleaned(contactInfoFromEditForm.getHome())));
        assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
        assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
    }
    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "")
                .replaceAll("[-()]","");
    }
}
