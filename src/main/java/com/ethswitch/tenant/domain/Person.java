package com.ethswitch.tenant.domain;

import com.ethswitch.tenant.crud.constants.GenderEnum;
import com.ethswitch.tenant.crud.domain.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.time.LocalDate;


@Getter
@Setter
@Entity
public class Person extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = -6366308706123143582L;

    private String firstName;

    private String middleName;

    private String lastName;

    private LocalDate birthDate;

    private GenderEnum gender;

    private String email;

    private String phoneNumber;

    @Transient
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
}
