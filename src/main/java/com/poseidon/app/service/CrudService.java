package com.poseidon.app.service;

import com.poseidon.app.persistence.mapper.CrudMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class CrudService<Model, Entity, Id> {
    protected abstract JpaRepository<Entity, Id> getRepository();

    protected abstract CrudMapper<Model, Entity> getMapper();

    protected abstract void setModelId(Model model, Id id);

    protected void prepareCreate(Model newModel) {
    }

    protected void prepareUpdate(Model newModel, Entity currentEntity) {
    }

    @Transactional(readOnly = true)
    public List<Model> list() {
        return getRepository().findAll().stream().map(getMapper()::toModel).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Model read(Id id) {
        Entity entity = getRepository().findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException(id);
        }
        return getMapper().toModel(entity);
    }

    @Transactional
    public Model create(Model model) {
        setModelId(model, null);
        prepareCreate(model);
        Entity entity = getMapper().fromModel(model);
        getRepository().save(entity);
        return getMapper().toModel(entity);
    }

    @Transactional
    public Model update(Id id, Model model) {
        Entity entity = getRepository().findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException(id);
        }
        setModelId(model, id);
        prepareUpdate(model, entity);
        getMapper().fromModel(entity, model);
        getRepository().save(entity);
        return getMapper().toModel(entity);
    }

    @Transactional
    public void delete(Id id) {
        Entity entity = getRepository().findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException(id);
        }
        getRepository().delete(entity);
    }

    @Getter
    public static class EntityNotFoundException extends RuntimeException {
        private final Object entityId;

        public EntityNotFoundException(Object entityId) {
            super("Invalid entity id: " + entityId);
            this.entityId = entityId;
        }
    }
}
