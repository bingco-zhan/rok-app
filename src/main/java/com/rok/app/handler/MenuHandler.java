package com.rok.app.handler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindDrag($(menu, ".menu", AnchorPane.class));
    }
}
