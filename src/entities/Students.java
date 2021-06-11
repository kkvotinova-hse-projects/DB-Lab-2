/*
 * Copyright (c) 2021 Ksuvot
 */

package entities;

public class Students {
    private String name;
    private int floor;
    private int room;

    public Students(String name, int floor, int room) {
        setName(name);
        setFloor(floor);
        setRoom(room);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }
}
