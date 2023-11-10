package com.example.restdocapp.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // Entity 클래스에는 Setter를 붙이지 말 것!!!
// Default 생성자가 없으면 JPA가 ORM을 못해준다!!!
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 신입이 Default 생성자를 못 때리게 막아줌
// protected User () {} 과 같음
@Entity
@Table(name = "user_tb")
public class User {
    @Id // javax.persistence임 아니면 Jakarta임
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @Column(length = 100, nullable = false) // 해시 때문에 password 60자 정도
    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    // // @Setter를 쓰지말고 의미 있는 이름으로 메서드를 만들자.
    // public void updatePassword(String password) {
    //     this.password = password;
    // }

    @Builder
    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}