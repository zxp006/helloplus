package com.zxp.helloplus.model;

import lombok.Data;

import java.util.Optional;

@Data
public class Address {
    private Optional<Street> street = Optional.empty();
}