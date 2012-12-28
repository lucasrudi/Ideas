package com.rudilucas.ideas.exception;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4907442976494965196L;

    private ObjectId resourceId;

    public ResourceNotFoundException(ObjectId resourceId) {
        this.resourceId = resourceId;
    }

    public ObjectId getResourceId() {
        return resourceId;
    }

}
