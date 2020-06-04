package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class EditAbstractController {

    @FXML
    private Button editButton;

    @FXML
    void initialize() {
        putData();
        editButton.setOnAction(this::onClickEdit);
    }

    abstract void putData();

    abstract void onClickEdit(ActionEvent e);
}
