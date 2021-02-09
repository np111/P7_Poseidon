package com.poseidon.app.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Rating {
    private Long id;
    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
    private Byte orderNumber;
}
