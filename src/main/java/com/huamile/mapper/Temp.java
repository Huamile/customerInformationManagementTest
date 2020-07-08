package com.huamile.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Afa
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temp {

    private Integer value;

    private String title;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
