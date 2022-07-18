package com.yasmanets.vcard

/**
 *   Created by Yaser El Dabete Arribas
 *   Date: 15/7/22
 */

import org.junit.Assert.assertEquals
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AddressTest {

    companion object {
        private var address = Address()
    }

    @Test
    fun testA_getStreet() {
        address.setStreet("One Apple Park Way")
        assertEquals(address.getStreet(), "One Apple Park Way")
    }

    @Test
    fun testB_getCity() {
        address.setCity("Cupertino")
        assertEquals(address.getCity(), "Cupertino")
    }

    @Test
    fun testC_getRegion() {
        address.setRegion("CA")
        assertEquals(address.getRegion(), "CA")
    }

    @Test
    fun testD_getPostalCode() {
        address.setPostalCode("95014")
        assertEquals(address.getPostalCode(), "95014")
    }

    @Test
    fun testE_getCountry() {
        address.setCountry("United States of America")
        assertEquals(address.getCountry(), "United States of America")
    }

    @Test
    fun testF_getFormattedAddress() {
        val actual = address.getFormattedAddress()
        val expected = ";;One Apple Park Way;Cupertino;CA;95014;United States of America"
        assertEquals(actual, expected)
    }

    @Test
    fun testG_toAddress() {
        val formatted = ";;One Apple Park Way;Cupertino;CA;95014;United States of America"
        val address = address.toAddress(formatted)
        assertEquals(address.getStreet(), "One Apple Park Way")
        assertEquals(address.getCity(), "Cupertino")
        assertEquals(address.getRegion(), "CA")
        assertEquals(address.getPostalCode(), "95014")
        assertEquals(address.getCountry(), "United States of America")
    }
}