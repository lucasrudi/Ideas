package com.rudilucas.ideas.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.security.Principal;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Component;

@Component(value = "userDetails")
public class User extends LdapUserDetailsImpl implements UserDetails, Externalizable, Principal {

    @Id
    private ObjectId id;
    @Indexed
    private String name;
    private String email;

    protected User() {
        super();
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String mail) {
        super();
        this.name = name;
        this.email = mail;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (ObjectId) in.readObject();
        name = (String) in.readObject();
        email = (String) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(email);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this.id.equals(((User) obj).getId())) {
            return true;
        }
        return super.equals(obj);
    }

}
