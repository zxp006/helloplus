package com.zxp.helloplus.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class Address {
    private Optional<Street> street = Optional.empty();
    private Integer id;
    private String desc;
}