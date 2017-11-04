package de.tb.showroom.pinterest.model;

import com.github.slugify.Slugify;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pinboard implements Serializable {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String slug;
    private boolean secret;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Pin.class)
    private Set<Pin> pins = new HashSet<>();

    public Pinboard() {}

    public Pinboard(String title) {
        setTitle(title);
    }

    public void addPin(Pin pin) {
        this.pins.add(pin);
    }

    public void addPin(String url) {
        this.pins.add(new Pin(url));
    }

    public void addPin(String url, String description) {
        this.pins.add(new Pin(url, description));
    }

    public void removePin(Pin pin) {
        this.pins.remove(pin);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.slug = new Slugify().slugify(title);
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public Set<Pin> getPins() {
        return Collections.unmodifiableSet(pins);
    }

    public void setPins(Set<Pin> pins) {
        this.pins = pins;
    }

    public String getSlug() {
        return slug;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.title)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Pinboard pinboardToCompareTo = (Pinboard) obj;
        return new EqualsBuilder()
                .append(this.title, pinboardToCompareTo.getTitle())
                .build();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.SIMPLE_STYLE)
                .append("id", id)
                .append("title", title)
                .append("slug", slug)
                .append("secret", secret)
                .build();
    }
}