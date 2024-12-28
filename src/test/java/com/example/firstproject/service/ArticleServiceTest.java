package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트하겠다
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 2. 실제 데이터
        List<Article> articles = articleService.index();
        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success() {   // 존재하는 id 입력
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail() {  // 존재하지 않는 id 입력
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.show(id);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }   // -> expected와 article 모두 null 반환하기 때문에 테스트 통과!

    @Test
    @Transactional
    void create_success() { // title과 content만 있는 dto 입력
        // 1. 예상 데이터
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);    // dto 생성
        Article expected = new Article(4L, title, content);         // 예상 데이터 저장
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_fail() {    // id가 포함된 dto 입력
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);  // dto 생성
        Article expected = null;                                // 예상 데이터 저장
        // 2. 실제 데이터
        Article article = articleService.create(dto);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }   // id가 포함된 게시물 생성 요청이 올 경우 실제 실행 결과와 예상 데이터 모두 null을 반환하기 때문에 테스트 통과!

    @Test
    @Transactional
    void update_success_yes_content() {     // 존재하는 id와 title, content가 있는 dto를 입력
        // 1. 예상 데이터
        Long id = 1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        // 2. 실제 데이터
        Article article = articleService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_success_no_content() {     // 존재하는 id와 title만 있는 dto를 입력
        // 1. 예상 데이터
        Long id = 1L;
        String title = "abcd";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(1L, "abcd", "1111");
        // 2. 실제 데이터
        Article article = articleService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_fail() {     // 존재하지 않는 id의 dto를 입력
        // 1. 예상 데이터
        Long id = -1L;
        String title = "가나다라";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.update(id, dto);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_success() {     // 존재하는 id를 입력
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 2. 실제 데이터
        Article article = articleService.delete(id);
        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_fail() {     // 존재하지 않는 id를 입력
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터
        Article article = articleService.delete(id);
        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

}