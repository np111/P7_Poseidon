package com.poseidon.app.http.controller;

import com.poseidon.app.model.RuleName;
import com.poseidon.app.persistence.entity.RuleNameEntity;
import com.poseidon.app.service.RuleNameService;
import lombok.Getter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(RuleNameController.class)
class RuleNameControllerTest extends AbstractCrudControllerTest<RuleNameService, RuleName, RuleNameEntity, Long> {
    @MockBean
    private @Getter RuleNameService service;

    public RuleNameControllerTest() {
        super("/ruleName", RuleName.class, Long.class);
    }

    @Override
    protected RuleName getModelA() {
        return RuleName.builder()
                .id(1L)
                .name("name")
                .description("description")
                .json("json")
                .template("template")
                .sqlStr("sqlStr")
                .sqlPart("sqlPart")
                .build();
    }

    @Override
    protected RuleName getModelB() {
        return RuleName.builder()
                .id(583352L)
                .name("fQQMUyhdfB")
                .description("8uNlqQUCE6")
                .json("TKNmqCNgmc")
                .template("d4uLWSNQ93")
                .sqlStr("wLmFRdlkBJ")
                .sqlPart("WbKnU8R4Mq")
                .build();
    }
}
