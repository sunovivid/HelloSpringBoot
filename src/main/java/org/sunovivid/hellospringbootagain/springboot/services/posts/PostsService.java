package org.sunovivid.hellospringbootagain.springboot.services.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sunovivid.hellospringbootagain.springboot.domain.posts.Posts;
import org.sunovivid.hellospringbootagain.springboot.domain.posts.PostsRepository;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsListResponseDto;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsResponseDto;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsSaveRequestDto;
import org.sunovivid.hellospringbootagain.springboot.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;


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
        return id; //리턴한 id -> 업데이트의
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //조회 기능만 사용해 조회 속도가 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        //map(PostsListResponseDto::new)는 map(posts -> new PostsListResponseDto(posts))와 같음
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
        //존재하는 글인지 엔티티 조회 후 그대로 삭제
    }
}
