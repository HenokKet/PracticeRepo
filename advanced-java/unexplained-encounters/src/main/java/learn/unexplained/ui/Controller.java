package learn.unexplained.ui;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.domain.EncounterResult;
import learn.unexplained.domain.EncounterService;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;

public class Controller {

    private final EncounterService service;
    private final View view;

    public Controller(EncounterService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.printHeader("Welcome To Unexplained Encounters.");

        try {
            runMenuLoop();
        } catch (DataAccessException ex) {
            view.printHeader("CRITICAL ERROR:" + ex.getMessage());
        }

        view.printHeader("Goodbye");
    }

    private void runMenuLoop() throws DataAccessException {
        MenuOption option;
        do {
            option = view.displayMenuGetOption();
            //Added the 3 new menu Options from the switch case
            switch (option) {
                case DISPLAY_ALL:
                    displayAllEncounters();
                    break;
                case DISPLAY_BY_TYPE:
                    displayByType();
                    break;
                case ADD:
                    addEncounter();
                    break;
                case UPDATE:
                    updateEncounter();
                    break;
                case DELETE:
                    deleteEncounter();
                    break;
            }
        } while (option != MenuOption.EXIT);
    }

    private void displayAllEncounters() throws DataAccessException {
        List<Encounter> encounters = service.findAll();
        view.printAllEncounters(encounters);
    }
    //display by type helper method
    private void displayByType() throws DataAccessException {
        EncounterType type = view.promptEncounterType();
        List<Encounter> list = service.findByType(type);
        view.printEncounters("Display By Type: " + type, list);
    }
    private void addEncounter() throws DataAccessException {
        Encounter encounter = view.makeEncounter();
        EncounterResult result = service.add(encounter);
        view.printResult(result);
    }
    private void updateEncounter() throws DataAccessException {
        //retrieves the type
        EncounterType type = view.promptEncounterType();
        List<Encounter> list = service.findByType(type);
        //Empty Check
        if (list.isEmpty()) {
            view.info("No encounters of type " + type + " found.");
            return;
        }
        //Retrieves the Encounter + Edge-case checks
        view.printEncounters("Select an Encounter to Update", list);
        int id = view.readInt("Enter encounter id to update: ");

        Encounter target = null;
        for (Encounter e : list) {
            if (e.getEncounterId() == id) {
                target = e;
                break;
            }
        }

        if (target == null) {
            view.info("Encounter id " + id + " not in the displayed list.");
            return;
        }
        //prints the edited encounter
        Encounter edited = view.editEncounter(target);
        EncounterResult result = service.update(edited);
        view.printResult(result);
    }

    private void deleteEncounter() throws DataAccessException {
        //retrieves the type
        EncounterType type = view.promptEncounterType();
        List<Encounter> list = service.findByType(type);
        //Empty check
        if (list.isEmpty()) {
            view.info("No encounters of type " + type + " found.");
            return;
        }
        //Retrieves the Encounter + Edge-case checks
        view.printEncounters("Select an Encounter to Delete", list);
        int id = view.readInt("Enter encounter id to delete: ");


        Encounter target = null;
        for (Encounter e : list) {
            if (e.getEncounterId() == id) {
                target = e;
                break;
            }
        }

        if (target == null) {
            view.info("Encounter id " + id + " not in the displayed list.");
            return;
        }

        //Prints the deleted encounter + ID
        boolean ok = service.deleteById(id);
        view.printDeleteResult(ok, id);
    }
}
