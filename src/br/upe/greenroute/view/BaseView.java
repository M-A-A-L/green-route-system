package br.upe.greenroute.view;

public abstract class BaseView {
    public void displayMessage(String message) {
        System.out.println("[INFO] "+ message);
    }
    public void displayError(String error) {
        System.out.println("[ERROR] "+ error);
    }
}
