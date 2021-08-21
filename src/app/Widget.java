package app;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class Widget {

    public static boolean OK(String msg) {
        return isCreated(createGenericWidget("CodeLab", msg,
                new ButtonType("OK", ButtonBar.ButtonData.OK_DONE)
        ));
    }

    private static Dialog<String> createGenericWidget(String title, String msg, ButtonType type) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setContentText(msg);
        dialog.getDialogPane().getButtonTypes().add(type);
        return dialog;
    }

    private static boolean isCreated(Dialog<String> dialog) {
        return dialog.showAndWait().isPresent();
    }

}
