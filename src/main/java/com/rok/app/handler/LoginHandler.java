package com.rok.app.handler;

import com.rok.app.pojo.Login;
import com.rok.app.utils.Validator;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h3>登录窗口
 * <p>
 * create: 2020/9/1 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class LoginHandler extends UiHandler implements Initializable {

    @FXML
    private AnchorPane header;

    @FXML
    private AnchorPane container;

    public static Scene build() throws IOException {
        Parent login = FXMLLoader.load(LoginHandler.class.getResource("/fxml/login.fxml"));
        return new Scene(login);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindDrag(header);
    }

    public void doSubmit(MouseEvent event) throws IOException {
        // 表单校验
//        Login login = serializer(".text-field", container, Login.class);
//        boolean hasError = Validator.V(login).onError((field, violation) -> {
//            if ("username".equals(field)) {
//                $(container, "#usernameLabel", Label.class)
//                        .setText(violation.getMessage());
//            } else if ("password".equals(field)) {
//                $(container, "#passwordLabel", Label.class)
//                        .setText(violation.getMessage());
//            }
//        });
//        if (hasError) {
//            return;
//        }

        // TODO - 后台登录操作

        // 登录成功
        Parent home = FXMLLoader.load(LoginHandler.class.getResource("/fxml/home.fxml"));
        EventTarget target = event.getTarget();
        Window window = ((Node) target).getScene()
                .getWindow();
        ((Stage) window).setScene(new Scene(home));
    }
}
