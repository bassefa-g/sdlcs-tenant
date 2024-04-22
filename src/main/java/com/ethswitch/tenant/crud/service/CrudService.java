package com.ethswitch.tenant.crud.service;

import com.ethswitch.tenant.crud.domain.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface CrudService<E extends AbstractEntity, P extends Serializable> {

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    E save(E entity);

    List<E> save(Iterable<E> entities);

    E update(P id, E entity);

    void softDelete(String uuid);

    void deleteAll(Iterable<E> entities);

    E findById(P id);

    E findByUuid(String uuid);

    Optional<E> findByIdOptional(P id);

    Optional<E> findByUuidOptional(String uuid);

    long count();

    void flushRepository();
}
