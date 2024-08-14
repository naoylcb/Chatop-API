package com.chatop.api.responses;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BadRequestResponse {

    private Timestamp timestamp;

    private Integer status;

    private String error;

    private String path;
}
