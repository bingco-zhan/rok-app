package com.rok.app;

import com.rok.app.handler.LoginHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * <h3>程序启动类
 * <p>
 * create: 2020/8/31 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class Bootstrap extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("rok");
        primaryStage.setScene(LoginHandler.build());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
