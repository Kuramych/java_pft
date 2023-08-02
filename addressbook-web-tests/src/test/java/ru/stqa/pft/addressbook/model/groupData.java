package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class groupData {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private final String name;
    private final String header;
    private final String footer;

    public groupData(String name, String header, String footer) {
        this.id = 0;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public groupData(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
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
