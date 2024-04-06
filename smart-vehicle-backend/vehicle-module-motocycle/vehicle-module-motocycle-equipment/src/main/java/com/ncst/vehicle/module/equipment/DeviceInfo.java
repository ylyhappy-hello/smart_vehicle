package com.ncst.vehicle.module.equipment;

public class DeviceInfo {
    private boolean isAlive;
    private long lastAliveTimestamp; // Added variable for last alive timestamp

    public DeviceInfo() {
        this.isAlive = true;
        this.lastAliveTimestamp = System.currentTimeMillis();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public long getLastAliveTimestamp() {
        return lastAliveTimestamp;
    }

    public void updateLastAliveTimestamp() {
        lastAliveTimestamp = System.currentTimeMillis();
    }
}
