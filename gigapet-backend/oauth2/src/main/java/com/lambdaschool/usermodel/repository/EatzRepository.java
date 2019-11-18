package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Eatz;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EatzRepository extends CrudRepository<Eatz, Long> {
    Eatz findByTitle(String title);

}
