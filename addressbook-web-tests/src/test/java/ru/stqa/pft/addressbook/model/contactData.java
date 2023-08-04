package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class contactData {

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




    public contactData withId(int id) {
        this.id = id;
        return this;
    }
    public contactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public contactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public contactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public contactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public contactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public contactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public contactData withAddress(String address) {
        this.address = address;
        return this;
    }
    public contactData withHomePhone(String home) {
        this.home = home;
        return this;
    }
    public contactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }
    public contactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public contactData withAllPhone(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        contactData that = (contactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    public contactData withGroup(String group) {
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

    @Override
    public String toString() {
        return "contactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
