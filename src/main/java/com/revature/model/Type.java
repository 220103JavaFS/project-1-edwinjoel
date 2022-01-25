package com.revature.model;

public class Type {
    private int typeId;
    private Type.State type;
    public enum State{
        LODGING,
        TRAVEL,
        FOOD,
        OTHER
    }

    //TODO: Make into Enum class
    public Type() {
    }

    public Type(int typeId, State type) {
        this.typeId = typeId;
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public State getType() {
        return type;
    }

    public void setType(State type) {
        this.type = type;
    }
}
