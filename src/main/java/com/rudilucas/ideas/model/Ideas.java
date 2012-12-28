package com.rudilucas.ideas.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Ideas implements Externalizable {
    @Id
    private ObjectId id;
    private String title;
    private String description;
    private User creator;
    private int positiveVotes;
    private int negativeVotes;
    private IdeaStatus status;
    @DBRef
    private transient List<Vote> votes;
    @DBRef
    private transient List<Ideas> mergedIdeas;

    @Deprecated
    /**
     * @deprecated to be used only for the modelAtrribute
     */
    public Ideas() {
        this.title = "";
        this.description = "";
        status = IdeaStatus.ACTIVE;
    }

    public Ideas(String title, String description) {
        this.title = title;
        this.description = description;
        status = IdeaStatus.ACTIVE;
    }

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public IdeaStatus getStatus() {
        return status;
    }

    public void setStatus(IdeaStatus status) {
        this.status = status;
    }

    public List<Ideas> getMergedIdeas() {
        return mergedIdeas;
    }

    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (ObjectId) in.readObject();
        title = (String) in.readObject();
        description = (String) in.readObject();
        positiveVotes = in.readInt();
        negativeVotes = in.readInt();
        votes = (List<Vote>) in.readObject();
        creator = (User) in.readObject();
        status = (IdeaStatus) in.readObject();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(title);
        out.writeObject(description);
        out.writeInt(positiveVotes);
        out.writeInt(negativeVotes);
        out.writeObject(votes);
        out.writeObject(creator);
        out.writeObject(status);
    }

    public void addVote(Vote vote) {
        if (votes == null) {
            votes = new ArrayList<Vote>();
        }
        votes.add(vote);
        if (vote.getType().equals(VoteType.POSITIVE)) {
            positiveVotes += 1;
        } else {
            negativeVotes += 1;
        }
    }

    public boolean isActive() {
        return status.equals(IdeaStatus.ACTIVE) || status.equals(IdeaStatus.IN_PROGRESS);
    }

    public void addMergedIdea(Ideas originIdea) {
        mergedIdeas.add(originIdea);
    }

}
