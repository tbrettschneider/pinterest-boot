package de.tb.showroom.pinterest.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Pinboard.class)
    private Set<Pinboard> pinboards = new HashSet<>();

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
        return pinboards;
    }

    public void setPinboards(Set<Pinboard> pinboards) {
        this.pinboards = pinboards;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.SIMPLE_STYLE)
                .append("id", id)
                .append("username", username)
                .build();
    }
}
