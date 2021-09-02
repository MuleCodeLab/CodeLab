package app.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Widget {

    private Widget() {}

    private static final String TITLE = "MULE-CodeLab";

    private static Alert create(String header, String info, AlertType type) {
        Alert dialog = new Alert(type);
        dialog.setTitle(TITLE);
        dialog.setHeaderText(header);
        dialog.setContentText(info);
        return dialog;
    }

    private static boolean show(Alert alert) {
        return alert != null && alert.showAndWait().isPresent();
    }

    public static boolean OK(String header, String info) {
        return show(create(header, info, AlertType.INFORMATION));
    }

    public static boolean ERROR(String header, String info) {
        return show(create(header, info, AlertType.ERROR));
    }

    public static void actionFailed(String info) {
        ERROR("Invalid Action", info);
    }
}
