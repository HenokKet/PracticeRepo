package org.example;

public class Gift {
    String description;
    String size;

    public Gift(String description, String size) {
        this.description = description;
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
