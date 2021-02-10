package com.poseidon.app.model;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class RuleName {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "json is mandatory")
    private String json;

    @NotBlank(message = "template is mandatory")
    private String template;

    @NotBlank(message = "sql is mandatory")
    private String sqlStr;

    @NotBlank(message = "sqlPart is mandatory")
    private String sqlPart;
}
