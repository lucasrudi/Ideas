package com.rudilucas.ideas.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Vote implements Externalizable {
	@Id
	private ObjectId voteId;
	private User user;
	private VoteType type;
	private String comment;

	public ObjectId getVoteId() {
		return voteId;
	}

	public void setVoteId(ObjectId voteId) {
		this.voteId = voteId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VoteType getType() {
		return type;
	}

	public void setType(VoteType type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		voteId = (ObjectId) in.readObject();
		user = (User) in.readObject();
		type = (VoteType) in.readObject();
		comment = (String) in.readObject();
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(voteId);
		out.writeObject(user);
		out.writeObject(type);
		out.writeObject(comment);
	}

}
