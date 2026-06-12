package br.upe.greenroute.controller;

public class BaseController {
    protected boolean validDouble (String text) {
        if (text == null || text.isBlank()) {
            return false;
        }
        return (text.matches("^[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?$"));
    }
    protected boolean validInt (String text) {
        if (text == null || text.isBlank()) {
            return false;
        }
        return (text.matches("^[+-]?\\d+$"));
    }
}
