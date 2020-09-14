package com.rok.app.ui;

import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * <h3>浮动窗口
 * <p>
 * create: 2020/9/14 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class BubbleStage extends Stage {

    private boolean isBind = false;

    public BubbleStage() {
        initStyle(StageStyle.TRANSPARENT);
    }

    public boolean bind(Node parent) {
        return bind(parent, false);
    }

    public boolean bind(Node parent, boolean overide) {
        if (!overide && isBind) return false;
        Window window = parent.getScene().getWindow();
        if (this == window) {
            return false;
        }
        initOwner(window);
        // 失去焦点隐藏
        this.focusedProperty().addListener((obs, o, n) -> {
            if (!n) this.hide();
        });
        return isBind = true;
    }
}
