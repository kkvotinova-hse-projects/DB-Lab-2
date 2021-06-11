/*
 * Copyright (c) 2021 Ksuvot
 */

package entities;

import java.sql.Date;

public class Household {
    private int id;
    private int rooms;
    private Date relevance;

    public Household(int id, int rooms, Date relevance) {
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

    public void setRelevance(Date relevance) {
        this.relevance = relevance;
    }

    public int getId() {
        return id;
    }

    public int getRooms() {
        return rooms;
    }

    public Date getRelevance() {
        return relevance;
    }
}
