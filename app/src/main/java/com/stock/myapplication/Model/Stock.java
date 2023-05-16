package com.stock.myapplication.Model;

public class Stock {
    String stockId;
    String stockName;
    String tradingVolume;//成交股數
    String tradingValue;//成交金額
    String openingPrice;//開盤價
    String highestPrice;//最高價
    String lowestPrice;//最低價
    String closingPrice;//收盤價
    String priceChange;//漲跌價差
    String numberofTrades;//成交筆數
    String DividendYield;//殖利率(%)
    String DividendYear;//股利年度
    String PEratio;//本益比
    String PBratio;//股價淨值比
    String FiscalYearQuarter;//財報年/季


    public Stock(String stockId, String stockName, String tradingVolume, String tradingValue
            , String openingPrice, String highestPrice
            , String lowestPrice, String closingPrice
            , String priceChange, String numberofTrades, String dividendYield, String dividendYear
            , String pEratio, String pBratio, String fiscalYearQuarter) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.tradingVolume = tradingVolume;
        this.tradingValue = tradingValue;
        this.openingPrice = openingPrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.closingPrice = closingPrice;
        this.priceChange = priceChange;
        this.numberofTrades = numberofTrades;
        this.DividendYield = dividendYield;
        this.DividendYear = dividendYear;
        this.PEratio = pEratio;
        this.PBratio = pBratio;
        this.FiscalYearQuarter = fiscalYearQuarter;
    }

    public String getStockId() {
        return stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public String getTradingVolume() {
        return tradingVolume;
    }

    public Long getTradingValue() {
        return Long.parseLong(tradingValue.replace(",", ""));
    }
    public String getTradingValue2() {
        return tradingValue;
    }

    public String getOpeningPrice() {
        return openingPrice;
    }

    public String getHighestPrice() {
        return highestPrice;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public String getClosingPrice() {
        return closingPrice;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public String getNumberOfTrades() {
        return numberofTrades;
    }

    public String getDividendYield() {
        return DividendYield;
    }

    public String getDividendYear() {
        return DividendYear;
    }

    public String getPEratio() {
        return PEratio;
    }

    public String getPBratio() {
        return PBratio;
    }

    public String getFiscalYearQuarter() {
        return FiscalYearQuarter;
    }


    public void setStockId(String stockId) {
        this.stockId = stockId;
    }


    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setTradingVolume(String tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    public void setTradingValue(String tradingValue) {
        this.tradingValue = tradingValue;
    }

    public void setOpeningPrice(String openingPrice) {
        this.openingPrice = openingPrice;
    }

    public void setHighestPrice(String highestPrice) {
        this.highestPrice = highestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public void setClosingPrice(String closingPrice) {
        this.closingPrice = closingPrice;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public void setNumberOfTrades(String numberofTrades) {
        this.numberofTrades = numberofTrades;
    }

    public void setDividendYield(String DividendYield) {
        this.DividendYield = DividendYield;
    }

    public void setDividendYear(String DividendYear) {
        this.DividendYear = DividendYear;
    }

    public void setPEratio(String PEratio) {
        this.PEratio = PEratio;
    }

    public void setPBratio(String PBratio) {
        this.PBratio = PBratio;
    }

    public void setFiscalYearQuarter(String FiscalYearQuarter) {
        this.FiscalYearQuarter = FiscalYearQuarter;
    }
}
