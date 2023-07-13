package me.jungeun.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jungeun.springbootdeveloper.config.jwt.JwtFactory;
import me.jungeun.springbootdeveloper.config.jwt.JwtProperties;
import me.jungeun.springbootdeveloper.domain.RefreshToken;
import me.jungeun.springbootdeveloper.domain.User;
import me.jungeun.springbootdeveloper.dto.CreateAccessTokenRequest;
import me.jungeun.springbootdeveloper.repository.RefreshTokenRepository;
import me.jungeun.springbootdeveloper.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TokenApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        userRepository.deleteAll();
    }

    @DisplayName("createNewAccessToken: 새로운 액세스 토큰을 발급한다.")
    @Test
    public void createNewAccessToken() throws Exception{
        //given
        final String url = "/api/token";

        // 유저의 이름
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());

        // 리프래쉬 토큰
        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", testUser.getId()))
                .build()
                .createToken(jwtProperties);

        // 리프레쉬 토큰의 디비에 저장
        refreshTokenRepository.save(new RefreshToken(testUser.getId(),
                refreshToken));

        // dto
        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
        request.setRefreshToken(refreshToken);
        // 요청 바디
        final String requestBody = objectMapper.writeValueAsString(request);

        // when
        // 여기의 결과는
        ResultActions resultActions = mockMvc.perform(post(url) // 요청을 보내고.
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody)); // 컨텐트 담기

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }
}
