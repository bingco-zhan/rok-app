package com.rok.app.handler;

import com.google.gson.Gson;
import com.rok.app.pojo.Entity;
import com.rok.app.utils.Assert;
import javafx.event.EventTarget;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * <h3>Ui组件处理基类
 * <p>
 * create: 2020/9/4 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public abstract class UiHandler {

    private double MX = 0;
    private double MY = 0;

    /**
     * 表单序列化成对象
     *
     * @param selector 元素选择器
     * @param clazz    序列化模板
     * @param <T>      数据转化对象
     */
    public <T> T serializer(String selector, Node node, Class<T> clazz) {
        Assert.notNull(clazz, "class can't be null: " + clazz);
        Set<Node> nodes = node.lookupAll(selector);
        try {
            Constructor<T> constructor = Assert.NotArgumentConstructor(clazz);
            T record = constructor.newInstance();
            for (Node n : nodes) {
                if (n instanceof TextField) {
                    TextField field = (TextField) n;
                    Object userData = field.getUserData();
                    Gson gson = new Gson();
                    Entity entity = gson.fromJson((String) userData, Entity.class);
                    BeanUtils.setProperty(record, entity.getName().trim(), field.getText().trim());
                }
            }
            return record;
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void bindMove(Node node) {
        node.setOnMousePressed(event -> {
            EventTarget target = event.getTarget();
            if (target.equals(node)) {
                Window window = ((Node) target).getScene().getWindow();
                MX = window.getX() - event.getScreenX();
                MY = window.getY() - event.getScreenY();
                node.setCursor(Cursor.CLOSED_HAND);
            }
        });

        node.setOnMouseDragged(event -> {
            EventTarget target = event.getTarget();
            if (target.equals(node)) {
                Window window = ((Node) target).getScene().getWindow();
                if (((Stage) window).isMaximized()) {
                    ((Stage) window).setMaximized(false);
                }
                window.setX(event.getScreenX() + MX);
                window.setY(event.getScreenY() + MY);
            }
        });

        node.setOnMouseReleased(event -> node.setCursor(Cursor.DEFAULT));
    }

    @SuppressWarnings("unchecked")
    public <T> T $(Node node, String selector, Class<T> clazz) {
        Node lookup = node.lookup(selector);
        if (clazz.isInstance(lookup))
            return (T) lookup;
        else return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T $(Node node, String selector, int index, Class<T> clazz) {
        Node[] nodes = node.lookupAll(selector).toArray(new Node[] {null});
        if (nodes.length < index) {
            return null;
        }
        Node lookup = nodes[index - 1];
        if (clazz.isInstance(lookup))
            return (T) lookup;
        else return null;
    }
}
