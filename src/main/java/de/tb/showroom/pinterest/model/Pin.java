package de.tb.showroom.pinterest.model;

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
    public String toString() {
        return new ToStringBuilder(ToStringStyle.SIMPLE_STYLE)
                .append("id", id)
                .append("description", description)
                .append("url", url)
                .build();
    }
}
