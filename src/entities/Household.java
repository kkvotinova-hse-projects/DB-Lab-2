/*
 * Copyright (c) 2021 Ksuvot
 */

package entities;

public class Household {
    private int id;
    private int rooms;
    private int relevance;

    public Household(int id, int rooms, int relevance) {
        setId(id);
        setRelevance(relevance);
        setRooms(rooms);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    public int getId() {
        return id;
    }

    public int getRooms() {
        return rooms;
    }

    public int getRelevance() {
        return relevance;
    }
}
