package com.api.auctionsniper;

import javax.swing.*;

public class MainWindow extends JFrame {

    static String MAIN_WINDOW_NAME = "Auction Sniper";

    public MainWindow() {
        super("Auction Sniper");
        setName(MAIN_WINDOW_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
