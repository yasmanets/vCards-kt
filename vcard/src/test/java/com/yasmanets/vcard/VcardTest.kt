package com.yasmanets.vcard

/**
 *   Created by Yaser El Dabete Arribas
 *   Date: 15/7/22
 */

import org.junit.Assert.*
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class VcardTest {

    companion object {
        private var vcard = VCard()
    }

    @Test
    fun testA_getDefaultVersion() {
        assertEquals(vcard.getVersion(), "3.0")
    }

    @Test
    fun testB_getVersion() {
        vcard.setVersion(VCard.VERSION.V3)
        assertEquals(vcard.getVersion(), "3.0")
    }

    @Test
    fun testC_getDefaultEncoding() {
        assertEquals(vcard.getEncoding(), "CHARSET=UTF-8")
    }

    @Test
    fun testD_getEncoding() {
        vcard.setEncoding(VCard.ENCODING.UTF8)
        assertEquals(vcard.getEncoding(), "CHARSET=UTF-8")
    }

    @Test
    fun testE_getPhoto() {
        vcard.setPhoto(
            VCard.FORMAT.PNG, "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")
        assertTrue(vcard.getPhoto().isNotEmpty())
    }

    @Test
    fun testF_getName() {
        vcard.setName("John")
        assertEquals(vcard.getName(), "John")
    }

    @Test
    fun testG_getSurname() {
        vcard.setSurname("Doe")
        assertEquals(vcard.getSurname(), "Doe")
    }

    @Test
    fun testH_getEmail() {
        vcard.setEmail("john@doe.com")
        assertEquals(vcard.getEmail(), "john@doe.com")
    }

    @Test
    fun testI_getTitle() {
        vcard.setTitle("Software Developer")
        assertEquals(vcard.getTitle(), "Software Developer")
    }

    @Test
    fun testJ_getRole() {
        vcard.setRole("Software Developer")
        assertEquals(vcard.getRole(), "Software Developer")
    }

    @Test
    fun testK_getOrganization() {
        vcard.setOrganization("ulompi")
        assertEquals(vcard.getOrganization(), "ulompi")
    }

    @Test
    fun testL_getOrganizationUrl() {
        vcard.setOrganizationUrl("https://www.ulompi.cards")
        assertEquals(vcard.getOrganizationUrl(), "https://www.ulompi.cards")
    }

    @Test
    fun testM_getPhone() {
        vcard.setPhone(VCard.TYPE.HOME,"123456789")
        vcard.setPhone(VCard.TYPE.WORK,"987654321")
        assertEquals(vcard.getPhone().size, 2)
        assertEquals(vcard.getPhone().keys, mutableSetOf("TYPE=HOME", "TYPE=WORK"))
    }

    @Test
    fun testN_getAddress() {
        val address = Address()
        address.setStreet("One Apple Park Way")
        address.setCity("Cupertino")
        address.setRegion("CA")
        address.setPostalCode("95014")
        address.setCountry("United States Of America")
        vcard.setAddress(VCard.TYPE.WORK, address)
        assertEquals(vcard.getAddress().size, 1)
        assertEquals(vcard.getAddress().keys, mutableSetOf("TYPE=WORK"))
    }

    @Test
    fun testO_getUrl() {
        vcard.setUrl("Linkedin","https://www.linkedin.com/company/ulompi")
        vcard.setUrl("Instagram","https://www.instagram.com/ulompi.cards/")
        assertEquals(vcard.getUrl().size, 2)
        assertEquals(vcard.getUrl().keys, mutableSetOf("Linkedin", "Instagram"))
    }

    @Test
    fun testP_getNotes() {
        vcard.setNote("Test notes")
        assertEquals(vcard.getNotes(), "Test notes")
    }

    @Test
    fun testP_generateAndroidVcard() {
        val vcf = vcard.androidVcard()
        assertTrue(vcf.contains("FN;"))
        assertTrue(vcf.contains("N;"))
        assertTrue(vcf.contains("URL;CHARSET=UTF-8:https://www.linkedin.com/company/ulompi"))
    }

    @Test
    fun testQ_generateIOSvCard() {
        val vcf = vcard.iOSVcard()
        assertTrue(vcf.contains("FN;"))
        assertTrue(vcf.contains("N;"))
        assertTrue(vcf.contains("item0.URL;CHARSET=UTF-8:https://www.linkedin.com/company/ulompi"))
        assertTrue(vcf.contains("item0.X-ABLABEL;CHARSET=UTF-8:Linkedin"))
    }
}
