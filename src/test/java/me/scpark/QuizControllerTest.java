package me.scpark;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc

class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("quiz(): GET /quiz?code=1 -> 응답 코드는 201, 응답 본문은 Created")
    @Test
    void getquiz() throws Exception {

        // 준비 given
        final String url = "/quiz";

        // 실행 when
        final ResultActions result =
                mockMvc.perform(get(url).param("code", "1"));

        // 검증 then
        result
                .andExpect(status().isCreated())
                .andExpect(content().string("Created"));
    }


    @DisplayName("POST: /quiz 요청, 요청 바디에 {'value':1}이면 응답 코드는 403, 응답 본문은 Forbidden")
    @Test
    void postQuiz1() throws Exception {

        // given
        final String url = "/quiz";


        // when
        final ResultActions result = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Code(1)))
        );

        // then
        result
                .andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden"));
    }
    record Code(int value) {}

    @DisplayName("quiz(): GET /quiz?code=2 -> 응답 코드는 400, 응답 본문은 bad request")
    @Test
    void getQuiz2() throws Exception {

        // 준비 given
        final String url = "/quiz";

        // 실행 when
        final ResultActions result =
                mockMvc.perform(get(url).param("code", "2"));

        // 검증 then
        result
                .andExpect(status().isBadRequest())
                .andExpect(content().string("bad request"));
    }
    @DisplayName("POST: /quiz 요청, 요청 바디에 {'value':2}이면 응답 코드는 200, 응답 본문은 ok")
    @Test
    void postQuiz2() throws Exception {

        // given
        final String url = "/quiz";


        // when
        final ResultActions result = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Code(2)))
        );

        // then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
}