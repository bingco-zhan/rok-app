package com.rok.app;

import com.rok.app.ui.EmojiTextArea;
import com.sun.javafx.stage.FocusUngrabEvent;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * <h3>
 * <p>
 * create: 2020/9/10 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class EmojiTextAreaTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        AnchorPane anchorPane = new AnchorPane();
        EmojiTextArea emojiTextArea = new EmojiTextArea();
        emojiTextArea.setPrefHeight(300);
        emojiTextArea.setPrefWidth(600);
        emojiTextArea.setBorder(new Border(
                new BorderStroke(Paint.valueOf("red"), BorderStrokeStyle.SOLID, null, new BorderWidths(1, 1, 1, 1)))
        );
        emojiTextArea.setBackground(new Background(new BackgroundFill(Paint.valueOf("green"), null, null)));
        // grid pane >> tool bar [top] & tool bar [buttom] & webview
        ObservableList<Node> childrenUnmodifiable = emojiTextArea.getChildrenUnmodifiable();
        Iterator<Node> iterator = childrenUnmodifiable.iterator();
        GridPane gridPane = (GridPane) iterator.next();
        ObservableList<Node> children = gridPane.getChildren();
        ToolBar topToolBar = (ToolBar) children.get(0);
        topToolBar.setVisible(false);
        topToolBar.setManaged(false);
        ToolBar buttomToolBar = (ToolBar) children.get(1);
        buttomToolBar.setVisible(false);
        buttomToolBar.setManaged(false);
        WebView webView = (WebView) children.get(2);
        WebEngine engine = webView.getEngine();
        engine.setUserStyleSheetLocation("data:,* {font: 13px 'Microsoft YaHei';}");
        Button button = new Button("obtain");
        button.setLayoutX(560);
        button.setLayoutY(300);

        webView.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            if (event.isControlDown() && event.getCode() == KeyCode.V) {
                final Clipboard systemClipboard = Clipboard.getSystemClipboard();
                System.out.println("Clipboard string: " + systemClipboard.getString());
                System.out.println("Clipboard html: " + systemClipboard.getHtml());
                System.out.println("Clipboard files: " + systemClipboard.getFiles());
                System.out.println("Clipboard image: " + systemClipboard.getImage());
                Set<DataFormat> contentTypes = systemClipboard.getContentTypes();
                System.out.println("type: " + contentTypes);
                String html = systemClipboard.getHtml();
                if (html != null && html.startsWith("<img ")) {
                    html = html.replace("<img", "<img draggable=\"false\" style=\"width: 25px; height: 25px\"");
                    System.out.println(html);
                    HashMap<DataFormat, Object> hashMap = new HashMap<>();
                    hashMap.put(DataFormat.HTML, html);
                    systemClipboard.setContent(hashMap);
                }
                System.out.println("================");
            }
        });
        anchorPane.getChildren().addAll(emojiTextArea, button);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("测试用例");
        primaryStage.show();
    }
}
