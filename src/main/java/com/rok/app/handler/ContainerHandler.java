package com.rok.app.handler;

import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h3>聊天区域控制器
 * <p>
 * create: 2020/9/8 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class ContainerHandler extends UiHandler implements Initializable {
    @FXML
    private AnchorPane container;

    @FXML
    private TextArea textEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindDrag($(container, "#container-bar", AnchorPane.class));
        AnchorPane edit = $(container, "#container-edit", AnchorPane.class);
        textEdit.focusedProperty()
                .addListener((abs, o, n) -> {
                    if (n) {
                        edit.getStyleClass().add("background-withe");
                    } else {
                        edit.getStyleClass().remove("background-withe");
                    }
                });
        Button button = $(edit, ".button", Button.class);
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> textEdit.requestFocus());
    }


    public void doTalk(MouseEvent event) {
        // 发送了空文本
        EventTarget target = event.getTarget();
        if (target instanceof Button) {
            Button button = (Button) target;
        }
    }
}
