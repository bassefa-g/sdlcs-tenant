package com.ethswitch.tenant.dto;

import com.ethswitch.tenant.crud.constants.GenderEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Locale;


@Getter
@Setter
public class PersonDto implements Serializable {

    private String uuid;
    private String firstName;
    private String middleName;
    private String lastName;
    private GenderEnum gender;
    private String email;
    private String phoneNumber;
    private Integer addressId;

    public String getFullName() {
        StringBuilder builder = new StringBuilder();

        if (StringUtils.hasText(this.firstName))
            builder.append(this.firstName);

        if (StringUtils.hasText(this.middleName))
            builder.append(" ").append(this.middleName);

        if (StringUtils.hasText(this.lastName))
            builder.append(" ").append(this.lastName);

        return builder.toString();
    }

    public String getFirstName() {
        return getName(firstName);
    }

    public String getMiddleName() {
        return getName(middleName);
    }

    public String getLastName() {
        return getName(lastName);
    }

    private String getName(String name) {
        return StringUtils.hasText(name) ? StringUtils.capitalize(name.toLowerCase(Locale.ROOT)) : null;
    }
}
