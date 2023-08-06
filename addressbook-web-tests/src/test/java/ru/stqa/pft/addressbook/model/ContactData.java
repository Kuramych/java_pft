package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {

    private  int  id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String title;
    private String company;
    private String address;
    private String group;
    private String home;
    private String work;
    private String mobile;
    private String phone2;
    private String allPhones;
    private String allEmails;
    private String email;
    private String email1;
    private String email2;
    private File photo;


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }
    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }
    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public ContactData withAllPhone(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }


    public String getFirstname() {
        return firstname;
    }
    public String getMiddlename() {
        return middlename;
    }
    public String getLastname() {
        return lastname;
    }
    public String getNickname() {
        return nickname;
    }
    public String getTitle() {
        return title;
    }
    public String getCompany() {
        return company;
    }
    public String getAddress() {
        return address;
    }
    public int getId() {
        return id;
    }
    public String getHome() {
        return home;
    }
    public String getWork() {
        return work;
    }
    public String getMobile() {
        return mobile;
    }
    public String getGroup() {
        return group;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public String getAllEmails() {
        return allEmails;
    }
    public String getEmail() {
        return email;
    }
    public String getEmail1() {
        return email1;
    }
    public String getEmail2() {
        return email2;
    }
    public File getPhoto() {
        return photo;
    }


    @Override
    public String toString() {
        return "contactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
