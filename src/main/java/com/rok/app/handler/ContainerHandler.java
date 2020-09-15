package com.rok.app.handler;

import com.rok.app.ui.BubblePane;
import com.rok.app.ui.BubbleStage;
import com.rok.app.utils.Talk;
import com.rok.app.utils.TalkBuilder;
import de.jensd.fx.glyphs.octicons.OctIconView;
import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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

    private BubbleStage faceFlowbox;

    public final static Map<String, String> faceMap = new HashMap<>();

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

        faceFlowbox = new BubbleStage();
        try {
            Parent load = FXMLLoader.load(ContainerHandler.class.getResource("/fxml/container/face_panel.fxml"));
            faceFlowbox.setScene(new Scene(load));
            ObservableList<Node> childrenUnmodifiable = load.getChildrenUnmodifiable();
            childrenUnmodifiable.forEach(node -> {
                node.hoverProperty().addListener((abs, o, n) -> {
                    if (n) {
                        node.getStyleClass().add("hover");
                    } else {
                        node.getStyleClass().remove("hover");
                    }
                });
                String code = (String) node.getUserData();
                node.setOnMouseClicked(event -> {
                    textEdit.appendText(code);
                });
                faceMap.put(code, $(node, ".image-view", ImageView.class).getImage().getUrl());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doTalk(MouseEvent event) throws IOException {
        // 发送了空文本
        String text = textEdit.getText();
        if (text.trim().isBlank()) {
            textEdit.setText("");
            tooltip.show();
        } else {
            ObservableList<Node> items = msgView.getItems();
            items.add(TalkBuilder.build(Talk.RIGHT_TALK, "骨王", text.trim()));
            msgView.scrollTo(items.size());
            textEdit.setText("");
        }
    }

    public void doFace(MouseEvent event) {
        EventTarget target = event.getTarget();
        if (!(target instanceof OctIconView)) {
            return;
        }
        boolean bind = faceFlowbox.bind(container);
        Window window = container.getScene().getWindow();
        double x = window.getX() + 280;
        double y = window.getY() + window.getHeight() - 260;
        faceFlowbox.setX(x);
        faceFlowbox.setY(y);
        faceFlowbox.show();
    }
}
