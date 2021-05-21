package com.api.auctionsniper;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

class FakeAuctionServer {
    private final SingleMessageListener messageListener = new SingleMessageListener();

    static final String ITEM_ID_AS_LOGIN = "auction-%s";
    static final String AUCTION_RESOURCE = "Auction";
    static final String XMPP_HOSTNAME = "localhost";
    static final String AUCTION_PASSWORD = "auction";

    private final String itemId;
    private final XMPPConnection connection;
    private Chat currentChat;

    FakeAuctionServer(String itemId) {
        this.itemId = itemId;
        this.connection = new XMPPConnection(XMPP_HOSTNAME);
    }

    void startSellingItem() throws XMPPException {
        connection.connect();
        connection.login(String.format(ITEM_ID_AS_LOGIN, itemId), AUCTION_PASSWORD, AUCTION_RESOURCE);
        connection.getChatManager().addChatListener(
                new ChatManagerListener() {
                    @Override public void chatCreated(Chat chat, boolean b) {
                        currentChat = chat;
                        chat.addMessageListener(messageListener);
                    }
                });
    }

    void hasReceivedJoinRequestFromSniper() throws InterruptedException {
        messageListener.receivesAMessage();
    }

    void announceClosed() throws XMPPException {
        currentChat.sendMessage(new Message());
    }

    void stop() {
        connection.disconnect();
    }

    String getItemId() {
        return itemId;
    }
}
