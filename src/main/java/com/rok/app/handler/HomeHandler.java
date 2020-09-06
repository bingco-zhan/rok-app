package com.rok.app.handler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * <h3>主界面
 * <p>
 * create: 2020/9/1 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class HomeHandler extends UiHandler implements Initializable {

    @FXML
    private AnchorPane home;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindMove($(home, ".menu", AnchorPane.class));
        bindMove($(home, "#list-top", AnchorPane.class));
        bindMove($(home, "#container-bar", AnchorPane.class));
        AnchorPane edit = $(home, "#container-edit", AnchorPane.class);
        $(edit, ".text-area", TextArea.class)
                .focusedProperty()
                .addListener((abs, o, n) -> {
                    if (n) {
                        edit.getStyleClass().add("background-withe");
                    } else {
                        edit.getStyleClass().remove("background-withe");
                    }
                });
        $(edit, ".button", Button.class)
                .addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
                    $(edit, ".text-area", TextArea.class)
                            .requestFocus();
                });
    }

    public void doSmall(MouseEvent event) {
        Window window = home.getScene()
                .getWindow();
        if (window instanceof Stage) {
            ((Stage) window).setIconified(true);
        }
    }

    public void doExit(MouseEvent event) {
        Window window = home.getScene()
                .getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
    }
}
