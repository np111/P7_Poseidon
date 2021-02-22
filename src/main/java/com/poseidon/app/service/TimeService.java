package com.poseidon.app.service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TimeService {
    public ZonedDateTime nowSecs() {
        return ZonedDateTime.now(ZoneOffset.UTC).withNano(0);
    }
}
