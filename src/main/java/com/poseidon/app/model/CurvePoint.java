package com.poseidon.app.model;

import java.time.ZonedDateTime;
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
    private Long curveId;
    private ZonedDateTime asOfDate;
    private Double term;
    private Double value;
    private ZonedDateTime creationDate;
}
