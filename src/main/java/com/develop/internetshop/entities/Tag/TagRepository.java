package com.develop.internetshop.entities.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TagRepository
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
}