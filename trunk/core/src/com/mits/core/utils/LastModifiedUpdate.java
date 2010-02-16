package com.mits.core.utils;

import com.mits.core.client.SuperEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class LastModifiedUpdate {
    @PrePersist
    public void onPersist(SuperEntity entityObject) {
        entityObject.updateCreated();
        entityObject.updateLastModified();
    }

    @PreUpdate
    public void onMerge(SuperEntity entityObject) {
        entityObject.updateLastModified();
        if (entityObject.getCreated() == null) {
            entityObject.updateCreated();
        }
    }
}
