package com.poseidon.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

public class CsrfTokenTestRepository implements CsrfTokenRepository {
    private static final String CSRF_ATTR = "_csrf";

    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", "12345678-903d-4d4e-8890-3e46ad33ce00");
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        if (token == null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(CSRF_ATTR);
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(CSRF_ATTR, token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (CsrfToken) session.getAttribute(CSRF_ATTR);
    }
}
