package ru.social.View.AlertDialog;

public interface MyAlertDialog {

    public void showAlertInfo(String textInfo);
    public void showProgressDialog();
    public boolean progressIsShowing();
    public void dismissProgressDialog();
}
