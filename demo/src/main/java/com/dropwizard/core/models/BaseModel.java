package com.dropwizard.core.models;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {
    private String id;
    private Long created;
    private Long modified;
    private boolean updated;
    private boolean deleted;

    public BaseModel() {
        this.setCreated(DateTime.now().getMillis());
        this.setModified(DateTime.now().getMillis());
        this.setDeleted(false);
    }

    public void prepare() {
        if(getId() == null) {
            setId(ObjectId.get().toString());
            this.setCreated(DateTime.now().getMillis());
            this.setModified(DateTime.now().getMillis());
            this.setDeleted(false);
        }else {
            this.setModified(DateTime.now().getMillis());
            this.setUpdated(false);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
