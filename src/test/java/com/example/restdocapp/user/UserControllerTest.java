package com.example.restdocapp.user;

import com.example.restdocapp.MyWithRestDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @DataJpaTest // DB 관련된 메모리만 로드됨, 컨트롤러는 로드 안됨
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
@SpringBootTest // 통합테스트, 얘가 실행될때 메모리에 모두 로드됨.
public class UserControllerTest extends MyWithRestDoc {

    // // @AutoConfigureMockMvc를 붙여줘야함.
    // @Autowired
    // private MockMvc mockMvc; // http 요청할때 쓰는 mvc

    @Test
    public void join_test() throws Exception {
        /* Given */
        UserRequest.JoinDTO requestDTO = new UserRequest.JoinDTO();
        requestDTO.setUsername("cos");
        requestDTO.setPassword("1234");
        requestDTO.setEmail("cos@nate.com");
        // 실제 통신시 Java 객체가 가지 않으니 JSON으로 변환해준다.
        ObjectMapper om = new ObjectMapper();
        String requestBody = om.writeValueAsString(requestDTO);

        /* When */
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/join")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // resultActions 안에 Header와 Body가 다 들어가 있음.
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        System.out.println("======================\n\n");
        System.out.println(responseBody);
        System.out.println("\n\n======================");

        /* Then */
        // 상태 검증 (틀린것부터 확인하고 맞는것을 확인하자.)
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.username").value("cos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.password").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.email").value("cos@nate.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }

    @Test
    public void user_info_test() throws Exception {
        // given
        int id = 1;

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/user/" + id));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.username").value("ssar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.password").value("1234"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.email").value("ssar@nate.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isEmpty())
                .andDo(MockMvcResultHandlers.print())
                .andDo(document);
    }
}
