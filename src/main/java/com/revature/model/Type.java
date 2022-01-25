package com.revature.model;

public class Type {
    private int typeId;
    private String type;

    //TODO: Make into Enum class
    public Type() {
    }

    public Type(int typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
