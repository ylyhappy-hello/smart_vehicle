package com.ncst.vehicle.module.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class EquimentService {
    
    public List<DeviceInfo> getList(){
        Map<String, DeviceInfo> m = DeviceInfoMapSingleton.getInstance();
        List<DeviceInfo> list = new ArrayList<>();
        m.forEach((k,v) -> {
            list.add(v);
        });
        return list;
    } 
}
