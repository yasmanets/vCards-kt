package com.yasmanets.vcard

/**
 *   Created by Yaser El Dabete Arribas
 *   Date: 15/7/22
 */

class Address {

    private var street:     String = ""
    private var city:       String = ""
    private var region:     String = ""
    private var postalCode: String = ""
    private var country:    String = ""

    fun setStreet(street: String) {
        this.street = street
    }

    fun getStreet(): String {
        return this.street
    }

    fun setCity(city: String) {
        this.city = city
    }

    fun getCity(): String {
        return this.city
    }

    fun setRegion(region: String) {
        this.region = region
    }

    fun getRegion(): String {
        return this.region
    }

    fun setPostalCode(postalCode: String) {
        this.postalCode = postalCode
    }

    fun getPostalCode(): String {
        return this.postalCode
    }

    fun setCountry(country: String) {
        this.country = country
    }

    fun getCountry(): String {
        return this.country
    }

    fun getFormattedAddress(): String {
        return String.format(";;%s;%s;%s;%s;%s", this.street, this.city, this.region, this.postalCode, this.country)
    }

    fun toAddress(data: String): Address {
        val address     = Address()
        val values      = data.split(";").filter { it.isNotEmpty() }
        address.street     = values[0]
        address.city       = values[1]
        address.region     = values[2]
        address.postalCode = values[3]
        address.country    = values[4]
        return address
    }
}