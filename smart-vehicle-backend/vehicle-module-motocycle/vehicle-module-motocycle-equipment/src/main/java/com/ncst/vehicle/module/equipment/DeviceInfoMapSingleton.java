package com.ncst.vehicle.module.equipment;
/**
 * DeviceInfoMap
 * 用来存储设备信息，单例模式，方便其他进程访问，并发安全
 */

import java.util.concurrent.ConcurrentHashMap;

public class DeviceInfoMapSingleton extends ConcurrentHashMap<String, DeviceInfo> {
    private static volatile DeviceInfoMapSingleton instance;

    private DeviceInfoMapSingleton() {
    }

    /**
     * Gets the singleton instance of DeviceInfoMapSingleton.
     *
     * @return The singleton instance.
     */
    public static DeviceInfoMapSingleton getInstance() {
        if (instance == null) {
            synchronized (DeviceInfoMapSingleton.class) {
                if (instance == null) {
                    instance = new DeviceInfoMapSingleton();
                }
            }
        }
        return instance;
    }
}
