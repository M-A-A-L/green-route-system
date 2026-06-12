package br.upe.greenroute.view;

public abstract class BaseMenu {
    public abstract void requestDataForCreate();
    public abstract void requestDataForRead();
    public abstract void requestDataForUpdate();
    public abstract void requestDataForDelete();
    public abstract void showMenu();
}
