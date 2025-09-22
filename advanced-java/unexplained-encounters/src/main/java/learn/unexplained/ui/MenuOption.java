package learn.unexplained.ui;

public enum MenuOption {
    //added the 3 new menu options
    EXIT("Exit"),
    DISPLAY_ALL("Display All Encounters"),
    DISPLAY_BY_TYPE("Display Encounters By Type"),
    ADD("Add An Encounter"),
    UPDATE("Update An Encounter"),
    DELETE("Delete An Encounter");

    private String message;

    MenuOption(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }
}
