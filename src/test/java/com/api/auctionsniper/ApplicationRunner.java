package com.api.auctionsniper;

public class ApplicationRunner {

    static final String SNIPER_ID = "sniper";
    static final String SNIPER_PASSWORD = "sniper";
    static final String XMPP_HOSTNAME = "localhost";
    private AuctionSniperDriver driver;

    void startBiddingIn(final FakeAuctionServer auction) {
        Thread thread = new Thread("Test Application") {
            @Override public void run() {
                try {
                    Main.main(XMPP_HOSTNAME, SNIPER_ID, SNIPER_PASSWORD, auction.getItemId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        driver = new AuctionSniperDriver(1000);
        driver.showsSniperStatus(Main.STATUS_JOINING);
    }

    void showsSniperHasLostAuction() {
        driver.showsSniperStatus(Main.STATUS_LOST);
    }

    void stop() {
        if(driver != null) {
            driver.dispose();
        }
    }
}
