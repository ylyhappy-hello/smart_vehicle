package com.ncst.vehicle.module.equipment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConcurrentServerWithThreadPool extends Thread {
    static private Map<String, DeviceInfo> deviceInfoMap = DeviceInfoMapSingleton.getInstance();

    @PostConstruct
    public void init(){
        start();
    }

    @Override
    public void run(){
        int portNumber = 12345;

        // Create a thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();

        // ScheduledExecutorService for handling device timeout
        ScheduledExecutorService timeoutExecutor = Executors.newScheduledThreadPool(1);

        // Schedule a task to check and mark devices as offline
        timeoutExecutor.scheduleAtFixedRate(() -> markDevicesOffline(deviceInfoMap), 30, 30, TimeUnit.SECONDS);

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            log.info("Server is listening on port " + portNumber);

            // Main thread handles incoming connections
            while (true) {
                Socket clientSocket = serverSocket.accept();
                log.info("Client connected: " + clientSocket.getInetAddress());

                // Use the thread pool to handle each client
                executorService.execute(new ClientHandler(clientSocket, ConcurrentServerWithThreadPool.deviceInfoMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Shutdown the thread pool and timeoutExecutor when the server is done
            executorService.shutdown();
            timeoutExecutor.shutdown();
        }
    }

    private static void markDevicesOffline(Map<String, DeviceInfo> deviceInfoMap) {
        long currentTime = System.currentTimeMillis();
        deviceInfoMap.forEach((deviceId, deviceInfo) -> {
            if (!deviceInfo.isAlive())
                return;
            if (currentTime - deviceInfo.getLastAliveTimestamp() > 30000) {
                deviceInfo.setAlive(false);
                log.warn("Device marked as offline: " + deviceId);
            }
        });
    }
}
