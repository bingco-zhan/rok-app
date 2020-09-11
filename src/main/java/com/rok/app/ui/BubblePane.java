package com.rok.app.ui;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * <h3>文本提示浮动框
 * <p>
 * create: 2020/9/8 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class BubblePane extends AnchorPane {

    private final ParallelTransition transition;

    public BubblePane() {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.35), this);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        BubblePane self = this;
        Timeline timeline = new Timeline();
        timeline.getKeyFrames()
                .add(new KeyFrame(Duration.seconds(3), event -> {
                    self.setVisible(false);
                }));
        this.transition = new ParallelTransition(this, fade, timeline);
    }

    public void show() {
        if (!isVisible()) {
            setVisible(true);
            transition.play();
        }
    }
}
