package com.maveric.thinknxt.stock.model;

public class OrdersSellBuy {

    private int sellCount;
    private int buyCount;

    public OrdersSellBuy(int sellCount, int buyCount) {
        this.sellCount = sellCount;
        this.buyCount = buyCount;
    }

    public OrdersSellBuy addBuy(int buyCount) {
        this.buyCount += buyCount;
        return this;
    }

    public OrdersSellBuy addSell(int sellCount) {
        this.sellCount += sellCount;
        return this;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }
}
