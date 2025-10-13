package learn.pets.models;

import java.util.Objects;

public class Pet {
    private int petId;
    private String name;
    private String type;

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return petId == pet.petId && Objects.equals(name, pet.name) && Objects.equals(type, pet.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, name, type);
    }
}
