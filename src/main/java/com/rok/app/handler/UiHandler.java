package com.rok.app.handler;

import com.google.gson.Gson;
import com.rok.app.pojo.Entity;
import com.rok.app.utils.Assert;
import javafx.event.EventTarget;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

    /**
     * 窗口拖动事件绑定
     *
     * @param source 组件
     */
    public void bindDrag(Node source) {
        source.setOnMousePressed(event -> {
            EventTarget target = event.getTarget();
            if (target.equals(source)) {
                Window window = ((Node) target).getScene().getWindow();
                MX = window.getX() - event.getScreenX();
                MY = window.getY() - event.getScreenY();
                source.setCursor(Cursor.CLOSED_HAND);
            }
        });

        source.setOnMouseDragged(event -> {
            EventTarget target = event.getTarget();
            if (target.equals(source)) {
                Window window = ((Node) target).getScene().getWindow();
                if (((Stage) window).isMaximized()) {
                    ((Stage) window).setMaximized(false);
                }
                window.setX(event.getScreenX() + MX);
                window.setY(event.getScreenY() + MY);
            }
        });

        source.setOnMouseReleased(event -> source.setCursor(Cursor.DEFAULT));
    }

    /**
     * 检索子组件
     *
     * @param parent   父组件
     * @param selector 选择器
     * @param clazz    子组件类型
     * @return 如果命中则放回 clazz 类型组件,否则返回null
     */
    @SuppressWarnings("unchecked")
    public <T> T $(Node parent, String selector, Class<T> clazz) {
        Node lookup = parent.lookup(selector);
        if (clazz.isInstance(lookup))
            return (T) lookup;
        else return null;
    }

    /**
     * 最小化
     *
     * @param event 鼠标事件
     */
    public void doSmall(MouseEvent event) {
        EventTarget target = event.getTarget();
        if (target instanceof Node) {
            Window window = ((Node) target).getScene()
                    .getWindow();
            if (window instanceof Stage) {
                ((Stage) window).setIconified(true);
            }
        }

    }

    /**
     * 最大化
     *
     * @param event 鼠标事件
     */
    public void doExit(MouseEvent event) {
        EventTarget target = event.getTarget();
        if (target instanceof Node) {
            Window window = ((Node) target).getScene()
                    .getWindow();
            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        }
    }
}
