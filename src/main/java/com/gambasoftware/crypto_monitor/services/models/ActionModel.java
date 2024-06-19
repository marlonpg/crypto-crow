package com.gambasoftware.crypto_monitor.services.models;

public class ActionModel {
    private ActionType action;
    private String userId;
    private String userName;
    private String symbol;
    private String price;
    private String amount;
    private String assetType;
    private String date;

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ActionModel{" +
                "action=" + action +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                ", amount='" + amount + '\'' +
                ", assetType='" + assetType + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
