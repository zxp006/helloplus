package com.zxp.helloplus.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class User {
     private String name;
     private Integer age;
     private Optional<Address> address = Optional.empty();


    public static  String getUserSteetName(User user) {
        Optional<User> userOptional = Optional.ofNullable(user);
        final String streetName = userOptional.orElse(new User()).getAddress().orElse(new Address()).getStreet().orElse(new Street()).getStreetName();
        return StringUtils.isEmpty(streetName) ? "nothing found" : streetName;
    }
 }