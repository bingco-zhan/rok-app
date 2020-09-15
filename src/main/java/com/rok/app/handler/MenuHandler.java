package com.rok.app.handler;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * <h3>菜单栏区域控制器
 * <p>
 * create: 2020/9/8 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class MenuHandler extends UiHandler implements Initializable {
    @FXML
    private AnchorPane menu;

    private FontAwesomeIconView activeIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindDrag($(menu, ".menu", AnchorPane.class));
        Set<Node> iconList = menu.lookupAll(".glyph-icon");
        for (Node node : iconList) {
            node.setOnMouseClicked(event -> {
                iconList.forEach(n -> {
                    ObservableList<String> styleClass = n.getStyleClass();
                    styleClass.remove("active");
                });
                node.getStyleClass().add("active");
                activeIcon = (FontAwesomeIconView) node;
            });
        }
    }
}
