package com.develop.internetshop.entities.Tag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * TagRepository
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
}