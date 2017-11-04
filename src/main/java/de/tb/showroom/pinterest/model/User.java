package de.tb.showroom.pinterest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Pinboard.class)
    private Set<Pinboard> pinboards = new HashSet<>();

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Pinboard> getPinboards() {
        return Collections.unmodifiableSet(pinboards);
    }

    public void setPinboards(Set<Pinboard> pinboards) {
        this.pinboards = pinboards;
    }

    public void addPinboard(Pinboard pinboard) {
        pinboard.setOwner(this);
        this.pinboards.add(pinboard);
    }

    public void removePinboard(Pinboard pinboard) {
        this.pinboards.remove(pinboard);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.SIMPLE_STYLE)
                .append("id", id)
                .append("username", username)
                .build();
    }
}
