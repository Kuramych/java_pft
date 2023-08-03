package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class groupData {

    private int id = Integer.MAX_VALUE;
    private String name;
    private String header;
    private String footer;

    public groupData withName(String name) {
        this.name = name;
        return this;
    }

    public groupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public groupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public groupData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        groupData groupData = (groupData) o;
        return id == groupData.id && Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "groupData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }


}
