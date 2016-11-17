/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.supersimplestocks.core.utils;

import org.joda.time.DateTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.supersimplestocks.core.enums.Action;
import org.supersimplestocks.core.enums.StockType;
import org.supersimplestocks.core.pojos.SimpleStock;
import org.supersimplestocks.core.pojos.Trade;

/**
 *
 * @author kurt
 */
public class GeneralUtilsTest {

    public GeneralUtilsTest() {
    }

    /**
     * Test of isNumeric method, of class GeneralUtils.
     */
    @Test
    public void testIsNumeric_True() {
        System.out.println("isNumeric");
        String str = "10";
        boolean result = GeneralUtils.isNumeric(str);
        assertTrue(result);
    }

    /**
     * Test of isNumeric method, of class GeneralUtils.
     */
    @Test
    public void testIsNumeric_False() {
        System.out.println("isNumeric");
        String str = "10a";
        boolean result = GeneralUtils.isNumeric(str);
        assertFalse(result);
    }

    /**
     * Test of getStock method, of class GeneralUtils.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        String[] input = {"TEA", "Common", "0", "100"};
        SimpleStock expResult = new SimpleStock("TEA", StockType.COMMON, 0, 0, 100);
        SimpleStock result = GeneralUtils.getStock(input);
        assertEquals(expResult.getSymbol(), result.getSymbol());
        assertEquals(expResult.getType(), result.getType());
        assertEquals(expResult.getLastDividend(), result.getLastDividend(), 0.0f);
        assertEquals(expResult.getParValue(), result.getParValue(), 0.0f);
    }

    /**
     * Test of getTrade method, of class GeneralUtils.
     */
    @Test
    public void testGetTrade() {
        System.out.println("getTrade");
        String input = "20/10/2012-15:28 100 sell 12";
        Trade expResult = new Trade(new DateTime(2012, 10, 20, 15, 28, 0, 0), 100, Action.SELL, 12);
        Trade result = GeneralUtils.getTrade(input);
        assertEquals(expResult.getAction(), result.getAction());
        assertEquals(expResult.getShares(), result.getShares(), 0.0f);
        assertEquals(expResult.getTradePrice(), result.getTradePrice(), 0.0f);
        assertEquals(expResult.getDate(), result.getDate());
    }

    /**
     * Test of isDateInPast15Mins method, of class GeneralUtils.
     */
    @Test
    public void testIsDateInPast15Mins_True() {
        System.out.println("isDateInPast15Mins");
        DateTime date = DateTime.now().minusMinutes(5);
        boolean result = GeneralUtils.isDateInPast15Mins(date);
        assertTrue(result);
    }

    /**
     * Test of isDateInPast15Mins method, of class GeneralUtils.
     */
    @Test
    public void testIsDateInPast15Mins_Future() {
        System.out.println("isDateInPast15Mins");
        DateTime date = DateTime.now().plusMinutes(20);
        boolean result = GeneralUtils.isDateInPast15Mins(date);
        assertTrue(result);
    }

    /**
     * Test of isDateInPast15Mins method, of class GeneralUtils.
     */
    @Test
    public void testIsDateInPast15Mins_False() {
        System.out.println("isDateInPast15Mins");
        DateTime date = DateTime.now().minusMinutes(20);
        boolean result = GeneralUtils.isDateInPast15Mins(date);
        assertFalse(result);
    }

}
