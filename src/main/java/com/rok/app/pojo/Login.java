package com.rok.app.pojo;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Size;

/**
 * <h3>登录数据模板
 * <p>
 * create: 2020/9/4 <br/>
 * email: bingco.zn@gmail.com <br/>
 *
 * @author zhan_bingcong
 * @version 1.0
 * @since jdk8+
 */
public class Login {

    @Length(min = 5, max = 17, message = "账号长度在[5~17]之间！")
    private String username;
    @Size(min = 5, max = 30, message = "秘密长度在[5~30]之间！")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
