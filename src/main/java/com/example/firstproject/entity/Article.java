package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // Article() 생성자를 대체하는 어노테이션 추가
@NoArgsConstructor  // 기본 생성자 추가 어노테이션
@ToString           // toString() 메서드를 대체하는 어노테이션 추가
@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
}
