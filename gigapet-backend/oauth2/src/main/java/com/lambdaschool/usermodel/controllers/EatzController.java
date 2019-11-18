package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.handlers.RestExceptionHandler;
import com.lambdaschool.usermodel.logging.Loggable;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Loggable
@RestController
@RequestMapping("/eatz")
@Api(tags = {"EatzEndpoint"})
public class EatzController {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
}
