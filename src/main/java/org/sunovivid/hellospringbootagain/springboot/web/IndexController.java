package org.sunovivid.hellospringbootagain.springboot.web;

import lombok.RequiredArgsConstructor;
import org.hibernate.graph.spi.AttributeNodeImplementor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.sunovivid.hellospringbootagain.springboot.config.auth.LoginUser;
import org.sunovivid.hellospringbootagain.springboot.config.auth.dto.SessionUser;
import org.sunovivid.hellospringbootagain.springboot.services.posts.PostsService;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsResponseDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        //model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장하는 역할
        //        //여기서는 posts 속성으로 index.mustache에게 전달 ({{#posts}}의 그것)
        //        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        //        //위의 코드는 @LoginUser 어노테이션으로 대체
        //CustomOAuth2UserService에서 로그인 성송 시 세션에 SessionUser를 저장하도록 구성함. 해당 값을 가져오는 것
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; //View Resolver에게 src/main/resources/templates/ + index + .mustache 전달 (mustache 플러그인에 의해 접두사, 접미사 붙음)
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
