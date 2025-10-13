package learn.pets.data;

import learn.pets.models.Pet;

import java.util.List;

public interface PetRepository {
    List<Pet> findAll();
    Pet findByName(String petName);
    Pet findById(int pet_id);
    Pet add(Pet pet);
    boolean update(Pet pet);
    boolean deleteByID(int petId);
}
