package de.tb.showroom.pinterest.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Pin implements Serializable {

    @Id @GeneratedValue
    private Long id;
    @URL
    private String url;
    private String description;

    public Pin() {}

    public Pin(String url) {
        this.url = url;
    }

    public Pin(String url, String description) {
        this(url);
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.url)
                .append(this.description)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Pin pinToCompareTo = (Pin) obj;
        return new EqualsBuilder()
                .append(this.url, pinToCompareTo.getUrl())
                .append(this.description, pinToCompareTo.getDescription())
                .build();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.SIMPLE_STYLE)
                .append("id", id)
                .append("description", description)
                .append("url", url)
                .build();
    }
}
