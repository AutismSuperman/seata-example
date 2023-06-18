package com.example.holder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultHolder {

    private static Map<String, String>  orderActionResults = new ConcurrentHashMap<>();

    /**
     * Set action one result.
     *
     * @param txId   the tx id
     * @param result the result
     */
    public static void setOrderActionResult(String txId, String result) {
        orderActionResults.put(txId, result);
    }

    /**
     * Get action one result string.
     *
     * @param txId the tx id
     * @return the string
     */
    public static String getOrderActionResult(String txId) {
        return orderActionResults.get(txId);
    }

}
