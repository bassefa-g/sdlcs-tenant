package com.ethswitch.tenant.crud.domain;


import com.ethswitch.tenant.crud.constants.DmlFlagEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity implements Serializable {

    public static final String FIELD_DATE_CREATED = "createdAt";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uuid;
    @Version
    private Integer version = 0;

    private Integer dmlFlag = DmlFlagEnum.CREATED.getValue();

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    protected AbstractEntity() {
        setUuid(UUID.randomUUID().toString());
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
        //TODO: audit
        this.updatedBy = "SYSTEM";
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
        //TODO: audit
        this.createdBy = "SYSTEM";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        AbstractEntity other = (AbstractEntity) object;

        return (this.getId() == null && other.getId() == null) || (this.getId() != null && this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [ID=" + id + "]";
    }
}

