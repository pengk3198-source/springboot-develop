package me.scpark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BackendApplication.class)
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MenberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    void getAllMembers() throws Exception {

        // given
        Menber m = new Menber(null, "SCPARK");
        Menber saveMember = memberRepository.save(m);

        // when
        ResultActions result = mockMvc.perform(
                get("/member")
                        .accept(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(saveMember.getid()))
                .andExpect(jsonPath("$[0].name").value(saveMember.getName()));
    }
}