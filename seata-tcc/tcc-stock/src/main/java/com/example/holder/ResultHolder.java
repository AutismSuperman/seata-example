package com.example.holder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultHolder {

    private static Map<String, String> stockActionResults = new ConcurrentHashMap<>();

    /**
     * Set action one result.
     *
     * @param txId   the tx id
     * @param result the result
     */
    public static void setStockActionResult(String txId, String result) {
        stockActionResults.put(txId, result);
    }

    /**
     * Get action one result string.
     *
     * @param txId the tx id
     * @return the string
     */
    public static String getStockActionResult(String txId) {
        return stockActionResults.get(txId);
    }

}
