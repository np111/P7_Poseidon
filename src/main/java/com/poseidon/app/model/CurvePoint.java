package com.poseidon.app.model;

import java.time.ZonedDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class CurvePoint {
    private Long id;

    @NotNull(message = "CurveId is mandatory")
    @Min(value = 1, message = "CurveId must be positive")
    private Long curveId;

    private ZonedDateTime asOfDate;

    @NotNull(message = "Term is mandatory")
    private Double term;

    @NotNull(message = "Value is mandatory")
    private Double value;

    private ZonedDateTime creationDate;
}
