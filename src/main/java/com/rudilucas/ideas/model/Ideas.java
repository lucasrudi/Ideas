package com.rudilucas.ideas.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Ideas implements Externalizable {
	@Id
	private ObjectId id;
	private String title;
	private String description;
	private int positiveVotes;
	private int negativeVotes;
	@Transient
	private transient List<Vote> votes;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPositiveVotes() {
		return positiveVotes;
	}

	public void setPositiveVotes(int positiveVotes) {
		this.positiveVotes = positiveVotes;
	}

	public int getNegativeVotes() {
		return negativeVotes;
	}

	public void setNegativeVotes(int negativeVotes) {
		this.negativeVotes = negativeVotes;
	}

	@SuppressWarnings("unchecked")
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		id = (ObjectId) in.readObject();
		title = (String) in.readObject();
		description = (String) in.readObject();
		positiveVotes = in.readInt();
		negativeVotes = in.readInt();
		votes = (List<Vote>) in.readObject();
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(id);
		out.writeObject(title);
		out.writeObject(description);
		out.writeInt(positiveVotes);
		out.writeInt(negativeVotes);
		out.writeObject(votes);
	}

}
