package com.rbeckett.gridlock.bootstrap;

import com.rbeckett.gridlock.utils.GridUtils;

public class AppDataGeneratorConfig {

    ////////////////////////////
    // These first 10 constants are dependent on other configurations, so it's best not to change them!
    ////////////////////////////

    public static final int NUM_LOCATIONS = 10;
    public static final int NUM_CONTACTS = 10;
    public static final int NUM_MANUFACTURERS = 10;
    public static final int NUM_BUSINESSES = 1;
    public static final int NUM_BUSINESS_UNITS = 10;
    public static final int NUM_SUPPORT_UNITS = 10;
    public static final int NUM_USERS = -1;
    public static final int NUM_USER_PROFILES = -1;
    public static final int NUM_PERMISSIONS = -1;
    public static final int NUM_ROLES = -1;

    ////////////////////////////
    // You can change these
    ////////////////////////////

    public static final int NUM_SERVICE_CONTRACTS = 10;
    public static final int NUM_SITES = 10;
    public static final int NUM_ROOMS = NUM_SITES * 2;
    public static final int NUM_RACKS = 1500;
    public static final int NUM_GRID_LOCATIONS = (int) (Math.pow(GridUtils.MAX_GRID_INDEX, 2)) / 3;
    public static final int NUM_DESKTOP_DEVICES = 250;
    public static final int NUM_MAIN_FRAMES = 500;
    public static final int NUM_NETWORK_DEVICES = 500;
    public static final int NUM_PATCH_PANELS = 250;
    public static final int NUM_SERVER_DEVICES = 1000;
    public static final int NUM_STORAGE_DEVICES = 500;
    public static final int NUM_STORAGE_FRAMES = 50;
    public static final int NUM_NETWORK_CONFIGURATIONS = NUM_DESKTOP_DEVICES + NUM_MAIN_FRAMES + NUM_NETWORK_DEVICES
            + NUM_SERVER_DEVICES + NUM_STORAGE_DEVICES + NUM_STORAGE_FRAMES;
    public static final int NUM_HARDWARE_CONFIGURATIONS = NUM_NETWORK_CONFIGURATIONS;
    public static final int NUM_OS_CONFIGURATIONS = NUM_NETWORK_CONFIGURATIONS;
    public static final int NUM_NETWORK_CONNECTIONS = NUM_NETWORK_CONFIGURATIONS * 3;
}
