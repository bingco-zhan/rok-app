package com.rok.app.handler;

import com.rok.app.ui.BubblePane;
import com.rok.app.utils.Talk;
import com.rok.app.utils.TalkBuilder;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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
    private ListView<Node> msgView;

    @FXML
    private TextArea textEdit;

    @FXML
    private BubblePane tooltip;

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


    public void doTalk(MouseEvent event) throws IOException {
        // 发送了空文本
        String text = textEdit.getText();
        if (text.trim().isBlank()) {
            textEdit.setText("");
            tooltip.show();
        } else {
            Node msgPane = TalkBuilder.build(Talk.RIGHT_TALK, "骨王", text.trim());
            ObservableList<Node> items = msgView.getItems();
            items.add(msgPane);
            msgView.scrollTo(items.size());
            textEdit.setText("");
        }
    }
}
