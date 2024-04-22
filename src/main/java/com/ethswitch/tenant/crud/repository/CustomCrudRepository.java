package com.ethswitch.tenant.crud.repository;

import com.ethswitch.tenant.crud.domain.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CustomCrudRepository<E extends AbstractEntity, L extends Serializable> extends JpaRepository<E, L> {

    Optional<E> findByUuid(String uuid);

    List<E> findAllByDmlFlagIsNullOrDmlFlagIsNot(int dmlFlag);

    Page<E> findAllByDmlFlagIsNullOrDmlFlagIsNot(Pageable pageable, int dmlFlag);
}
