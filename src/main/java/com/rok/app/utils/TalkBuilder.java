package com.rok.app.utils;

import com.rok.app.handler.ContainerHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TalkBuilder {

    private static final URL LeftMsgUrl = ContainerHandler.class.getResource("/fxml/container/left_msg.fxml");
    private static final URL RightMsgUrl = ContainerHandler.class.getResource("/fxml/container/right_msg.fxml");
    private static final Pattern FACE_PATTERN = Pattern.compile("\\[f[0-9]{1,2}]");

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
        Matcher matcher = FACE_PATTERN.matcher(msg);
        ObservableList<Node> children = textFlow.getChildren();
        int index = 0;
        while (matcher.find()) {
            String face = matcher.group();
            int i = msg.indexOf(msg, index);
            if (ContainerHandler.faceMap.containsKey(face)) {
                if (index != i) {
                    children.add(new Text(msg.substring(index, i)));
                    index = i;
                }
                ImageView imageView = new ImageView(ContainerHandler.faceMap.get(face));
                imageView.setFitWidth(23.0);
                imageView.setFitHeight(23.0);
                children.add(imageView);
                index += face.length();
            }
        }
        if (index != msg.length()) {
            children.add(new Text(msg.substring(index)));
        }
        return result;
    }
}
