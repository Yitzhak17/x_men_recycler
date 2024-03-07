package com.example.xmenrecycler;

public class DataModel {
    private String name; // Name of the data model
    private String description; // Description of the data model
    private int image; // Image resource ID of the data model
    private int id; // ID of the data model

    // Constructor to initialize the DataModel object
    public DataModel(String name, String description, int image, int id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.id = id;
    }

    // Getter method for retrieving the name of the data model
    public String getName() {
        return name;
    }

    // Getter method for retrieving the description of the data model
    public String getDescription() {
        return description;
    }

    // Getter method for retrieving the image resource ID of the data model
    public int getImage() {
        return image;
    }

    // Getter method for retrieving the ID of the data model
    public int getId() {
        return id;
    }

    // Setter method for setting the name of the data model
    public void setName(String name) {
        this.name = name;
    }

    // Setter method for setting the description of the data model
    public void setDescription(String description) {
        this.description = description;
    }

    // Setter method for setting the image resource ID of the data model
    public void setImage(int image) {
        this.image = image;
    }

    // Setter method for setting the ID of the data model
    public void setId(int id) {
        this.id = id;
    }
}
