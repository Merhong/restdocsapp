package com.example.restdocapp.user;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRequest {

    @Data // Getter, Setter, toString() 한방에 있음
    public static class JoinDTO {
        @Size(min = 3, max = 20)
        @NotEmpty // not null과 공백불가(Validation꺼 사용)
        private String username;

        @Size(min = 4, max = 20)
        @NotEmpty
        private String password;

        @Size(min = 10, max = 100)
        @NotEmpty
        private String email;

        // 팩토리 패턴!!!
        // DB에 Insert할때 필요한 부분을 Builder로 사용
        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }


    @Data // Getter, Setter, toString() 한방에 있음
    public static class LoginDTO {
        @Size(min = 3, max = 20)
        @NotEmpty // not null과 공백불가(Validation꺼 사용)
        private String username;

        @Size(min = 4, max = 20)
        @NotEmpty
        private String password;

        @Size(min = 10, max = 100)
        @NotEmpty
        private String email;
    }

}
