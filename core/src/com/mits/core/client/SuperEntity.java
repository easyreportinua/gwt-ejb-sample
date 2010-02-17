package com.mits.core.client;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(LastModifiedUpdate.class)
public class SuperEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    protected Date created;

    @Column(name = "last_modified")
    protected Date lastModified;

    @Version
    private Long version;

    public long getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void updateLastModified() {
        lastModified = new Date();
    }

    public void updateCreated() {
        created = new Date();
    }

}
