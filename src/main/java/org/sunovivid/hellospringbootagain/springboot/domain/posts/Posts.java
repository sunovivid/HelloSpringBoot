package org.sunovivid.hellospringbootagain.springboot.domain.posts;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sunovivid.hellospringbootagain.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter //lombok
@NoArgsConstructor //lombok. 기본 생성자 추가 (public Posts(){})
@Entity //JPA. 테이블과 링크될 클래스 (ex: SalesManagers.java -> sales_manages 테이블)
public class Posts extends BaseTimeEntity {
    
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성규칙
    private Long id; //Long으로 설정시 MySQL기준 BigInt로 설정됨..
    
    @Column(length = 500, nullable = false) //컬럼. 추가 설정 있을때 명시
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    private String author;
    
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /**
     * 이 메서드는 JPA의 영속성 컨텍스트 (엔티티를 영구 저장하는 환경) 때문에 쿼리를 날리는 내용이 없음
     * 엔티티의 데이터 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경된 내용을 반영
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
