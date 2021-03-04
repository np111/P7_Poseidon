package com.poseidon.app.persistence.mapper;

import org.mapstruct.MappingTarget;

/**
 * Mapper used to transform an entity into its model (and vice versa).
 *
 * @param <Model>  The model (eg. a DTO)
 * @param <Entity> The entity (eg. a JPA entity)
 */
public interface CrudMapper<Model, Entity> {
    /**
     * Transforms an entity into the corresponding model.
     *
     * @param entity an entity
     * @return a corresponding model
     */
    Model toModel(Entity entity);

    /**
     * Transforms a model into the corresponding entity.
     *
     * @param model a model
     * @return a corresponding entity
     */
    Entity fromModel(Model model);

    /**
     * Transforms a model into the corresponding entity.
     *
     * @param targetEntity the target entity, which will become a corresponding entity of the model
     * @param model        a model
     */
    void fromModel(@MappingTarget Entity targetEntity, Model model);
}
