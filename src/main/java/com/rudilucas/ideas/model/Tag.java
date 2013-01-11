package com.rudilucas.ideas.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Tag implements Externalizable {

    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        tag = (String) in.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(tag);
    }
    
    @Override
    public boolean equals(Object obj) {
        return tag.equals(((Tag)obj).getName());
    }
}
