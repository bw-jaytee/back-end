package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.logging.Loggable;
import com.lambdaschool.usermodel.models.Eatz;
import com.lambdaschool.usermodel.repository.EatzRepository;
import com.lambdaschool.usermodel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Loggable
@Transactional
@Service(value = "eatzService")
public class EatzServiceImpl implements EatzService {

    @Autowired
    private EatzRepository eatzRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Eatz> findAll() {
        List<Eatz> list = new ArrayList<>();
        eatzRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Eatz findEatzById(long id) {
        return eatzRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Eatz findEatztByTitle(String title) {
        Eatz eatz = eatzRepository.findByTitle(title);

        if (eatz == null)
        {
            throw new EntityNotFoundException("Restaurant " + title + " not found!");
        }

        return eatz;
    }
    @Transactional
    @Override
    public void delete(long id) {
        if (eatzRepository.findById(id).isPresent())
        {
            eatzRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Eatz save(@NotNull Eatz eatz, long id) {
        Eatz newEatz = new Eatz();

        newEatz.setTitle(eatz.getTitle());
        newEatz.setCarbs(eatz.getCarbs());
        newEatz.setFats(eatz.getFats());
        newEatz.setProteins(eatz.getProteins());

        newEatz.setUser(userRepository.findUserByUserid(id));
        return eatzRepository.save(newEatz);
    }

    @Transactional
    @Override
    public Eatz update(@NotNull Eatz eatz, long id) {
        Eatz currentEatz = eatzRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (eatz.getTitle() != null)
        {
            currentEatz.setTitle(eatz.getTitle());
        }

        currentEatz.setProteins(eatz.getProteins());

        currentEatz.setCarbs(eatz.getCarbs());

        currentEatz.setFats(eatz.getFats());
        currentEatz.setUser(userRepository.findUserByUserid(id));

        System.out.println(currentEatz.toString());
        return eatzRepository.save(currentEatz);
    }
}
