package com.rudilucas.ideas.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class User implements Externalizable {
	@Id
	private ObjectId id;
	private String name;
	private String email;

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

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		id = (ObjectId) in.readObject();
		name = (String) in.readObject();
		email = (String) in.readObject();
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(id);
		out.writeObject(name);
		out.writeObject(email);
	}

}
