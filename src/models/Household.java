/*
 * Copyright (c) 2021 Ksuvot
 */

package models;

public class Household {
    private int id;
    private int rooms;
    private int relevance;

    public Household(int id, int rooms, int relevance) {
        setId(id);
        setRooms(rooms);
        setRelevance(relevance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }
}
