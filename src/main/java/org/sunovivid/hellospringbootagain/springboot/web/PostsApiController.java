package org.sunovivid.hellospringbootagain.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.sunovivid.hellospringbootagain.springboot.services.posts.PostsService;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsResponseDto;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsSaveRequestDto;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long save(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findByID(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id; //TODO: 리턴한 값은 어디에 사용??
    }
}
