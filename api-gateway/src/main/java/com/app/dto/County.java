package com.app.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class County {

    private String id;
    private String name;
    private String voivodeshipId;
}
