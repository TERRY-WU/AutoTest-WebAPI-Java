package com.api.model;

import lombok.Data;

@Data
public class NewsModel {
    private String id;
    private String type;
    private String key;
    private String reason;
    private String status_code;

}
