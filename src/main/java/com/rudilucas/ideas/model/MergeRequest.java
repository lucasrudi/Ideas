package com.rudilucas.ideas.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class MergeRequest implements Externalizable {

    @Id
    private ObjectId id;
    @DBRef
    @Indexed
    private Ideas originIdea;
    @DBRef
    @Indexed
    private Ideas destinationIdea;
    private Date requestedDate;
    private Date acceptedDate;

    public MergeRequest(Ideas originIdea, Ideas destinationIdea) {
        this.originIdea = originIdea;
        this.destinationIdea = destinationIdea;
        requestedDate = new Date();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Ideas getOriginIdea() {
        return originIdea;
    }

    public void setOriginIdea(Ideas originIdea) {
        this.originIdea = originIdea;
    }

    public Ideas getDestinationIdea() {
        return destinationIdea;
    }

    public void setDestinationIdea(Ideas destinationIdea) {
        this.destinationIdea = destinationIdea;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (ObjectId) in.readObject();
        originIdea = (Ideas) in.readObject();
        destinationIdea = (Ideas) in.readObject();
        requestedDate = (Date) in.readObject();
        acceptedDate = (Date) in.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(originIdea);
        out.writeObject(destinationIdea);
        out.writeObject(requestedDate);
        out.writeObject(acceptedDate);
    }

}
