package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.handlers.RestExceptionHandler;
import com.lambdaschool.usermodel.logging.Loggable;
import com.lambdaschool.usermodel.models.Eatz;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.services.EatzService;
import com.lambdaschool.usermodel.services.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Loggable
@Controller
@RequestMapping("/eatz")
//@Api(tags = {"EatzEndpoint"})
public class EatzController {

   // private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Autowired
    private EatzService eatzService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/alleatzforuser",produces = {"application/json"})
    public ResponseEntity<?> listAllEatz(
            Authentication authentication) {
        String username =authentication.getName();
        User user = userService.findByName(username);
        List<Eatz> myEatz = user.getUsereatz();
        return new ResponseEntity<>(myEatz, HttpStatus.OK);
    }

    @GetMapping(value = "/id/{eatzId}",
            produces = {"application/json"})
    public ResponseEntity<?> getEatzById(
            @PathVariable
                    Long eatzId)
    {
        Eatz e = eatzService.findEatzById(eatzId);
        //ArrayList e = new ArrayList<Eatz>();
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{title}",
            produces = {"application/json"})
    public ResponseEntity<?> getEatzByName(
            @PathVariable
                    String title)
    {
        List<Eatz> e = eatzService.findEatztByTitle(title);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @PostMapping(value = "/create",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewEatz(@Valid
                                              @RequestBody
                                                      Eatz newEatz,
                                        Authentication authentication) throws URISyntaxException
    {
        User user = userService.findByName(authentication.getName());
        newEatz = eatzService.save(newEatz,user);
        HttpHeaders responseHeaders = new HttpHeaders();

        URI newEatzURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{eatztid}").buildAndExpand(newEatz.getEatzid()).toUri();
        responseHeaders.setLocation(newEatzURI);

        System.out.println(newEatz.toString());
        return new ResponseEntity<>(newEatz, responseHeaders, HttpStatus.OK);
    }
    /* Eatz finalEatz = eatzService.findEatzById(newEatz.getEatzid());
        user.getUsereatz().add(newEatz);
          System.out.println(user.toString());
          userService.updateEatz(user,user.getUserid());*/
    // set the location header for the newly created resource
       /* */
    /**/
    @PutMapping(value = "/update/{eatzid}")
    public ResponseEntity<?> updateEatz(
            @RequestBody
                    Eatz updateEatz,
            @PathVariable
                    long eatzid)
    {
        Eatz eatz = eatzService.update(updateEatz, eatzid);
        return new ResponseEntity<>(eatz, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{eatzid}")
    public ResponseEntity<?> deleteEatzById(
            @PathVariable
                    long eatzid)
    {
        eatzService.delete(eatzid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
