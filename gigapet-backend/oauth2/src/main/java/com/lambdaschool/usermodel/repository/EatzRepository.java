package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Eatz;
import org.springframework.data.repository.CrudRepository;

public interface EatzRepository extends CrudRepository<Eatz, Long> {
    Eatz findByTitle(String title);
}
