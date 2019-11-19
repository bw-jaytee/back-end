package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.Eatz;
import com.lambdaschool.usermodel.models.User;

import java.util.List;

public interface EatzService {
    List<Eatz> findAll();

    Eatz findEatzById(long id);

    Eatz findEatztByTitle(String name);

    void delete(long id);

    Eatz save(Eatz eatz, User user);

    Eatz update(Eatz eatz, long id);
}
