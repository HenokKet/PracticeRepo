package org.example.model;

public class MenuChoice {
    public enum MENU_CHOICE {
        ADD_MONEY,
        GET_CHANGE,
        PRODUCT,
        ERROR,
        VIEW_CASH, CASH_OUT, QUIT
    }

    private MENU_CHOICE choice;
    private String product;

    public MENU_CHOICE getChoice() {
        return choice;
    }

    public void setChoice(MENU_CHOICE choice) {
        this.choice = choice;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
