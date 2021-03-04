package com.poseidon.app.service;

import com.poseidon.app.persistence.mapper.CrudMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generic service to manage the CRUD operations of a model (DTO) and store it as a JPA entity.
 *
 * @param <Model>  The model (DTO)
 * @param <Entity> The JPA entity
 * @param <Id>     The type of primary ID of the entity
 */
public abstract class CrudService<Model, Entity, Id> {
    private final Sort defaultSort;

    public CrudService() {
        defaultSort = getDefaultSort();
    }

    /**
     * Returns the repository used to access and store the {@linkplain Entity JPA entities}.
     *
     * @return the repository
     */
    protected abstract JpaRepository<Entity, Id> getRepository();

    /**
     * Returns the mapper used to convert {@linkplain Model models} into {@linkplain Entity entities} (and vice versa).
     *
     * @return the mapper
     */
    protected abstract CrudMapper<Model, Entity> getMapper();

    /**
     * Set the model ID.
     * <p>
     * Should probably be implemented as: <pre>{@code model.setId(id)}</pre>
     *
     * @param model The model whose ID will be modified
     * @param id    The ID to set
     */
    protected abstract void setModelId(Model model, Id id);

    /**
     * Returns the default sorting applied when returning an entity list.
     *
     * @return the default sort
     */
    protected Sort getDefaultSort() {
        return Sort.by("id").ascending();
    }

    /**
     * This method is called at the time of the CREATE operation, just before mapping the {@linkplain Model model} to an
     * {@linkplain Entity entity}.
     * <p>
     * You can therefore apply changes to this new model before the entity is created.
     *
     * @param newModel The new model that will be created
     */
    protected void prepareCreate(Model newModel) {
    }

    /**
     * This method is called at the time of the UPDATE operation, just before mapping the {@linkplain Model model} to an
     * {@linkplain Entity entity}.
     * <p>
     * You can therefore apply changes to this new model before the entity is created.
     *
     * @param newModel      The new model that will be created
     * @param currentEntity The existing entity that will be replaced.
     */
    protected void prepareUpdate(Model newModel, Entity currentEntity) {
    }

    /**
     * Returns a list of all the entities in the repository.
     *
     * @return a list of all the entities
     */
    @Transactional(readOnly = true)
    public List<Model> list() {
        return getRepository().findAll(defaultSort).stream().map(getMapper()::toModel).collect(Collectors.toList());
    }

    /**
     * Returns the entity with the specified ID.
     *
     * @param id the ID of the entity to be returned
     * @return the entity with the specified ID
     * @throws EntityNotFoundException if no entity match this ID
     */
    @Transactional(readOnly = true)
    public Model read(Id id) {
        Entity entity = getRepository().findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException(id);
        }
        return getMapper().toModel(entity);
    }

    /**
     * Creates a new entity.
     * <p>
     * If set, the model ID will be ignored. A new ID will be generated each time (according to the repository method).
     *
     * @param model the model of the new entity to create
     * @return the model of the created entity (with ID defined)
     */
    @Transactional
    public Model create(Model model) {
        setModelId(model, null);
        prepareCreate(model);
        Entity entity = getMapper().fromModel(model);
        getRepository().save(entity);
        return getMapper().toModel(entity);
    }

    /**
     * Update an existing entity.
     * <p>
     * If set, the model ID will be ignored. The current entity ID will always be kept.
     *
     * @param id    the ID of the existing entity to update
     * @param model the new model
     * @return the model of the updated entity
     * @throws EntityNotFoundException if no entity match this ID
     */
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

    /**
     * Delete an entity.
     *
     * @param id the ID of the existing entity to delete
     * @throws EntityNotFoundException if no entity match this ID
     */
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
