package org.sunovivid.hellospringbootagain.springboot.config.auth.dto;

import lombok.Getter;
import org.sunovivid.hellospringbootagain.springboot.domain.user.User;

import java.io.Serializable;

//직렬화 기능을 가진 세션 dto
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
