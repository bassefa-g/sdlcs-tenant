package com.ethswitch.tenant.crud.service;

import com.ethswitch.tenant.crud.constants.DmlFlagEnum;
import com.ethswitch.tenant.crud.domain.AbstractEntity;
import com.ethswitch.tenant.crud.repository.CustomCrudRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


public abstract class CrudServiceImpl<E extends AbstractEntity, P extends Serializable, R extends CustomCrudRepository<E, P>>
        implements CrudService<E, P> {

    protected final R repository;

    @Autowired
    public CrudServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> findAll() {
        return repository.findAllByDmlFlagIsNullOrDmlFlagIsNot(DmlFlagEnum.DELETED.getValue());
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAllByDmlFlagIsNullOrDmlFlagIsNot(pageable, DmlFlagEnum.DELETED.getValue());
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public List<E> save(Iterable<E> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public E update(P id, E entity) {
        var e = repository.findById(id).orElseThrow(
                getEntityNotFoundExceptionSupplier("id", id));
        return repository.save(e);
    }

    @Override
    public void softDelete(String uuid) {
        var object = findByUuidOptional(uuid).orElseThrow(getEntityNotFoundExceptionSupplier("uuid", uuid));
        object.setDmlFlag(DmlFlagEnum.DELETED.getValue());
        repository.save(object);
    }

    @Override
    public void deleteAll(Iterable<E> entities) {
        repository.deleteAllInBatch(entities);
    }

    @Override
    public E findById(P id) {
        return findByIdOptional(id).orElseThrow(getEntityNotFoundExceptionSupplier("id", id));
    }

    @Override
    public E findByUuid(String uuid) {
        return findByUuidOptional(uuid).orElseThrow(getEntityNotFoundExceptionSupplier("uuid", uuid));
    }

    @Override
    public Optional<E> findByIdOptional(P id) {
        return repository.findById(id);
    }

    @Override
    public Optional<E> findByUuidOptional(String uuid) {
        return repository.findByUuid(uuid);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void flushRepository() {
        repository.flush();
    }

    private static Supplier<EntityNotFoundException> getEntityNotFoundExceptionSupplier(String name, Object value) {
        return () -> new EntityNotFoundException("Entity not found with " + name + " " + value);
    }
}
