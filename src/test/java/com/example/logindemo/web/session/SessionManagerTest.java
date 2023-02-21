package com.example.logindemo.web.session;

import com.example.logindemo.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void session_manager_세션생성_세션조회_세션만료() {

        MockHttpServletResponse response = new MockHttpServletResponse();
        // 세션 생성
        Member member = new Member("test1", "test1", "test");
        sessionManager.createSession(member, response);

        // Request에 쿠키 담기 (브라우저에서 해주는 작업)
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        // 쿠키의 값으로 세션에서 조회
        Object result = sessionManager.getSession(request);
        Assertions.assertThat(result).isEqualTo(member);

        // 세션 만료
        sessionManager.expire(request);
        Object session = sessionManager.getSession(request);
        Assertions.assertThat(session).isNull();
    }

}
