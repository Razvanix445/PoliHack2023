package com.example.polihackapp.Repositories;

import com.example.polihackapp.Domain.User;
import java.util.Optional;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> - type of entities saved in repository
 */
public interface Repository<ID, E extends User> {

    /**
     *
     * @param id - the id of the entity to be returned
     *                 id must not be null
     * @return the entity with the specified id
     *         or null if there is no entity with the given id
     * @throws IllegalArgumentException - if id is null
     */
    Optional<E> findOne(ID id);

    Optional<E> findOne(String usernames);

    /**
     * @return all entities
     */
    Iterable<E> findAll();

    /**
     * saves the given entity in repository
     * @param entity - entity must not be null
     * @return null - if the given entity is saved
     *                otherwise returns the entity (id already exists)
     * @throws com.example.polihackapp.Validators.ValidatorException - if the entity is not valid
     * @throws IllegalArgumentException - if the given entity is null
     */
    Optional<E> save(E entity);

    /**
     * removes the entity with the specified id
     * @param id - id must not be null
     * @return the removed entity or null if there is no entity with the given id
     * @throws IllegalArgumentException - if the given id is null
     */
    Optional<E> delete(ID id);
    Optional<E> delete(String userid);

    /**
     *
     * @param entity - entity must not be null
     * @return null - if the entity is updated
     *                otherwise returns the entity - (e.g. id does not exist)
     * @throws IllegalArgumentException - if the given entity is null
     * @throws com.example.polihackapp.Validators.ValidatorException - if the entity is not valid
     */
    Optional<E> update(E entity);
}
