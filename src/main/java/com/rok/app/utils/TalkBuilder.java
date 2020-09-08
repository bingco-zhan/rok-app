package com.rok.app.utils;

import com.rok.app.handler.ContainerHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;

public class TalkBuilder {

    private static final URL LeftMsgUrl = ContainerHandler.class.getResource("/fxml/container/left_msg.fxml");
    private static final URL RightMsgUrl = ContainerHandler.class.getResource("/fxml/container/right_msg.fxml");

    public static Node build(Talk talk, String name, String msg) throws IOException {
        AnchorPane result;
        switch (talk) {
            case LEFT_TALK:
                result = FXMLLoader.load(LeftMsgUrl);
                break;
            case RIGHT_TALK:
                result = FXMLLoader.load(RightMsgUrl);
                break;
            default:
                throw new IllegalArgumentException("unknown Talk enum: " + talk);
        }
        Label nameLabel = (Label) result.lookup(".item-msg-title > .label");
        nameLabel.setText(name);
        TextFlow textFlow = (TextFlow) result.lookup(".item-msg-bubble");
        // TODO - 解析msg
        Label msgLabel = new Label();
        textFlow.getChildren().add(msgLabel);
        msgLabel.setText(msg);
        return result;
    }
}
