
package com.spotify.repository;

import java.util.*;

public interface Repository<T> {

    void save(T entity);

    Optional<T> findById(UUID id);

    List<T> findAll();
}
