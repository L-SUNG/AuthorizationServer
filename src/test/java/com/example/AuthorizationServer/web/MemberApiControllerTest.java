package com.example.authorizationserver.web;

import com.example.authorizationserver.domain.member.Member;
import com.example.authorizationserver.domain.member.MemberRepository;
import com.example.authorizationserver.web.dto.MemberSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    // 테스트 종료 후
    @After
    public void tearDown() throws Exception{
        // 리포지토리의 모든 데이터 삭제
        memberRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void MemberRegister() throws Exception {
        String id = "id";
        String pass = "pass";
        String name = "name";
        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                .id(id)
                .pass(pass)
                .name(name)
                .build();

        String url = "http://localhost:" + port + "/api/v1/member";

        // Mock을 사용한 post송신
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Member> all = memberRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.get(0).getPass()).isEqualTo(pass);
        assertThat(all.get(0).getName()).isEqualTo(name);
    }
}
