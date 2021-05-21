package com.api.auctionsniper;

import javax.swing.*;

public class Main {
    static String STATUS_JOINING = "";
    static String STATUS_LOST = "";
    static String SNIPER_STATUS_NAME = "";

    private MainWindow ui;

    public Main() throws Exception {
        startUserInterface();
    }

    public static void main(String... args) throws Exception {
        Main main = new Main();
    }

    private void startUserInterface() throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override public void run() {
                ui = new MainWindow();
            }
        });
    }
}
