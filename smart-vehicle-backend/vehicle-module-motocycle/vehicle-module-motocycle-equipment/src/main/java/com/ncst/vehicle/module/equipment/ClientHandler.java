package com.ncst.vehicle.module.equipment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket clientSocket;

    private Map<String, DeviceInfo> deviceInfoMap;

    public ClientHandler(Socket clientSocket, Map<String, DeviceInfo> deviceInfoMap) {
        this.clientSocket = clientSocket;
        this.deviceInfoMap = deviceInfoMap;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Message received from client: " + inputLine);
                handleMessage(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleMessage(String message) {
        // Split the message using commas as separators
        String[] parts = message.split(",");
        if (parts.length == 3) {
            String deviceId = parts[0].trim();
            String action = parts[1].trim();
            String data = parts[2].trim();

            // Update device information
            System.out.println("设备id是" + deviceId);

            updateDeviceInfo(deviceId);
            System.out.println(DeviceInfoMapSingleton.getInstance());
            // Process the message based on the action
            switch (action) {
                case "alive":
                    handleAliveMessage(deviceId);
                    break;
                case "pushed":
                    handlePushedMessage(deviceId, data);
                    break;
                // Add more cases for different actions as needed
                default:
                    System.err.println("Unknown action: " + action);
                    break;
            }

        } else {
            System.err.println("Invalid message format: " + message);
        }
    }

    private void updateDeviceInfo(String deviceId) {
        DeviceInfo deviceInfo = deviceInfoMap.computeIfAbsent(deviceId, k -> new DeviceInfo());
        deviceInfo.setAlive(true);
    }

    private void handleAliveMessage(String deviceId) {
        // Handle alive message
        DeviceInfo deviceInfo = deviceInfoMap.get(deviceId);
        if (deviceInfo != null) {
            System.out.println("Alive message received from device: " + deviceId);
        }
    }

    private void handlePushedMessage(String deviceId, String data) {
        // Handle pushed message
        DeviceInfo deviceInfo = deviceInfoMap.get(deviceId);
        if (deviceInfo != null) {
            System.out.println("Pushed message received from device: " + deviceId + ", Data: " + data);
            // TODO: Add logic to insert data into the database
        }
    }
}
