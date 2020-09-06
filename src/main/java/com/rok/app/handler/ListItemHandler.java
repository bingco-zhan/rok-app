package com.rok.app.handler;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class ListItemHandler extends UiHandler implements Initializable {

    @FXML
    private AnchorPane item;

    private ContextMenu menu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 加载聊天好友信息

        // 右键菜单项
        menu = new ContextMenu();
        MenuItem menuItem = new MenuItem("删除聊天");
        menu.getItems().add(menuItem);

        // 绑定右键事件
        item.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                menu.show(item, Side.BOTTOM, event.getX(), -(item.getPrefHeight() - event.getY()));
            } else {
                menu.hide();
            }
        });

        // 删除聊天好友项
        menuItem.setOnAction(event -> {
            ListCell<?> parent = (ListCell<?>) item.getParent();
            ListView<?> listView = parent.getListView();
            listView.getItems().remove(item);
        });
    }
}
