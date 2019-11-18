package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.usermodel.logging.Loggable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/*
*
*
*
*
*
*
*
* */





@Loggable
@Entity
@Table(name = "eatz")
public class Eatz extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eatzid;

    @Column(nullable = false)
    private String title;


}
/*

public class Books  extends Auditable {



    @Column(nullable = false,
            unique = true)
    private String ISBN;

    private int copy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionid",
            nullable = false)
    @JsonIgnoreProperties("books")
    private Section section;

    @ManyToMany
    @JoinTable(name = "wrote",
            joinColumns = @JoinColumn(name = "bookid"),
            inverseJoinColumns = @JoinColumn(name = "authorid"))
    @JsonIgnoreProperties("authorBooks")
    private List<Authors> authors = new ArrayList<>();*/
