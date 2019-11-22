package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


/*
*       Eatz:
*
*   describes an individual meal for a gigapet,
*
*   as of 11/18 3 macronutrient fields are tracked, with 4th likely but blankable
*           fats :: integer
*           carbs :: integer //Carbohydrates
*           proteins :: integer
*           title :: string
*
*   id & createddate should be self evident
*
*
* */





@ApiModel(value = "Eatz",
        description = "Describes a meal for a gigapet")
@Entity
@Table(name = "eatz")

public class Eatz extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eatzid;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date createdDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int carbs;

    @Column(nullable = false)
    private int proteins;

    @Column(nullable = false)
    private int fats;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties("usereatz")//({"usereatz", "hibernateLazyInitializer"})
    private User user;
    /*
    * {
    *   "title" : "title",
    *   "carbs" : 5,
    *   "protiens": 3,
    *   "fats" : 3
    * }
    * */

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Eatz() {
    }

    public Eatz(String title, int carbs, int proteins, int fats) {
        this.title = title;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getEatzid() {
        return eatzid;
    }

    public void setEatzid(long eatzid) {
        this.eatzid = eatzid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    @Override
    public String toString() {
        return "Eatz{" +
                "eatzid=" + eatzid +
                ", title='" + title + '\'' +
                ", carbs=" + carbs +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", user=" + user +
                '}';
    }
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
