package com.poseidon.app.persistence.mapper;

import org.mapstruct.MappingTarget;

public interface CrudMapper<Model, Entity> {
    Model toModel(Entity entity);

    Entity fromModel(Model model);

    void fromModel(@MappingTarget Entity targetEntity, Model model);
}
