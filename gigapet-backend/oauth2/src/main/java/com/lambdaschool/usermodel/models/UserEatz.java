package com.lambdaschool.usermodel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lambdaschool.usermodel.logging.Loggable;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/*@Loggable
@Entity
@Table(name = "usereatz",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userid", "eatzid"})})
@ApiModel(value = "Usereatz",
        description = "Which user has what eatz")
public class UserEatz implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userroles")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "eatzid")
    @JsonIgnoreProperties("userroles")
    private Eatz eatz;

    public UserEatz()
    {
    }

    public UserEatz(User user,
                     Eatz eatz)
    {
        this.user = user;
        this.eatz = eatz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Eatz getEatz() {
        return eatz;
    }

    public void setEatz(Eatz eatz) {
        this.eatz = eatz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEatz)) return false;
        UserEatz userEatz = (UserEatz) o;
        return Objects.equals(getUser(), userEatz.getUser()) &&
                Objects.equals(getEatz(), userEatz.getEatz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getEatz());
    }

    @Override
    public String toString() {
        return "UserEatz{" +
                "user=" + user +
                ", eatz=" + eatz +
                '}';
    }
}
*/