package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResponseCaptcha implements Serializable {
    private boolean success;
    private String challenge_ts;
    private String hostname;
    private double score;
    private String action;
}