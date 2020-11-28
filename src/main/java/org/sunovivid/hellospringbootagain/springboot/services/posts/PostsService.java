package org.sunovivid.hellospringbootagain.springboot.services.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sunovivid.hellospringbootagain.springboot.domain.posts.Posts;
import org.sunovivid.hellospringbootagain.springboot.domain.posts.PostsRepository;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsResponseDto;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsSaveRequestDto;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsUpdateRequestDto;


@RequiredArgsConstructor //생성자 생성. 생성자가 빈 객체 받으므로 스프링에 주입
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }
}
