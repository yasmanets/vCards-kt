package vcard

import com.yasmanets.vcard.Address
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

/**
 *   Created by Yaser El Dabete Arribas
 *   Date: 15/7/22
 */


class VCard {

    companion object {
        private const val VCARD_BEGIN   = "BEGIN:VCARD\n"
        private const val VCARD_END     = "END:VCARD"
    }

    private var version:            String                      = "3.0"
    private var encoding:           String                      = "CHARSET=UTF-8"
    private var photo:              String                      = ""
    private var name:               String                      = ""
    private var surname:            String                      = ""
    private var email:              String                      = ""
    private var title:              String                      = ""
    private var role:               String                      = ""
    private var organization:       String                      = ""
    private var phone:              HashMap<String, String>     = HashMap()
    private var addresses:          HashMap<String, Address>    = HashMap()
    private var url:                HashMap<String, String>     = HashMap()
    private var organizationUrl:    String                      = ""
    private var notes:              String                      = ""

    enum class VERSION (val value: String) {
        V3("3.0")
    }

    enum class ENCODING (val value: String) {
        UTF8("CHARSET=UTF-8"),
        ISO("CHARSET=ISO-8859-1")
    }

    enum class TYPE (val value: String) {
        WORK("TYPE=WORK"),
        HOME("TYPE=HOME")
    }

    enum class FORMAT (val value: String) {
        PNG("TYPE=image/png"),
        JPG("TYPE=image/jpg"),
        JPEG("TYPE=image/jpeg")
    }

    fun setVersion(version: VERSION) {
        this.version = version.value
    }

    fun getVersion(): String {
        return this.version
    }

    fun setEncoding(encoding: ENCODING) {
        this.encoding = encoding.value
    }

    fun getEncoding(): String {
        return this.encoding
    }

    fun setPhoto(format: FORMAT, photo: String) {
        this.photo = String.format("ENCODING=b;%s:%s", format.value, photo)
    }

    fun getPhoto(): String {
        return this.photo
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return this.name
    }

    fun setSurname(surname: String) {
        this.surname = surname
    }

    fun getSurname(): String {
        return this.surname
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail(): String {
        return this.email
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getTitle(): String {
        return this.title
    }

    fun setRole(role: String) {
        this.role = role
    }

    fun getRole(): String {
        return this.role
    }

    fun setOrganization(organization: String) {
        this.organization = organization
    }

    fun getOrganization(): String {
        return this.organization
    }

    fun setOrganizationUrl(organizationUrl: String) {
        this.organizationUrl = organizationUrl
    }

    fun getOrganizationUrl(): String {
        return this.organizationUrl
    }

    fun setPhone(type: TYPE, phone: String) {
        this.phone[type.value] = phone
    }

    fun getPhone(): HashMap<String, String> {
        return this.phone
    }

    fun setAddress(type: TYPE, address: Address) {
        this.addresses[type.value] = address
    }

    fun getAddress(): HashMap<String, Address> {
        return this.addresses
    }

    fun setUrl(label: String, url: String) {
        this.url[label] = url
    }

    fun getUrl(): HashMap<String, String> {
        return this.url
    }

    fun setNote(notes: String) {
        this.notes = notes
    }

    fun getNotes(): String {
        return this.notes
    }

    fun generateAndroidVcard(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        var vcf = ""
        vcf += "BEGIN:VCARD\n"
        vcf += String.format("VERSION:%s\n", this.getVersion())
        vcf += String.format("FN;%s:%s %s\n", this.getEncoding(), this.getName(), this.getSurname())
        vcf += String.format("N;%s:%s;%s;;;\n",this.getEncoding(), this.getSurname(), this.getName())
        vcf += String.format("EMAIL;%s;type=WORK,INTERNET:%s\n", this.getEncoding(), this.getEmail())

        if (this.getPhoto().isNotEmpty()) {
            vcf += String.format("PHOTO;%s\n", this.getPhoto())
        }

        if (this.getPhone().size > 0) {
            for (phone in this.getPhone()) {
                vcf += String.format("TEL;%s,VOICE:%s\n", phone.key, phone.value)
            }
        }

        if (this.getAddress().size > 0) {
            for (address in this.getAddress()) {
                vcf += String.format("ADR;%s;%s:%s", this.getEncoding(), address.key, address.value.getFormattedAddress())
            }
        }

        if (this.getTitle().isNotEmpty()) {
            vcf += String.format("TITLE;%s:%s\n", this.getEncoding(), this.getTitle())
        }

        if (this.getRole().isNotEmpty()) {
            vcf += String.format("ROLE;%s:%s\n", this.getEncoding(), this.getRole())
        }

        if (this.getOrganization().isNotEmpty()) {
            vcf += String.format("ORG;%s:%s\n", this.getEncoding(), this.getOrganization())
        }

        if (this.getOrganizationUrl().isNotEmpty()) {
            vcf += String.format("URL;%s;%s:%s\n", TYPE.WORK.value, this.getEncoding(), this.getOrganizationUrl())
        }

        if (this.getUrl().size > 0) {
            for (url in this.getUrl()) {
                vcf += String.format("URL;%s:%s\n", this.getEncoding(), url.value)
            }
        }

        if (this.getNotes().isNotEmpty()) {
            vcf += String.format("NOTE;%s:%s\n", this.getEncoding(), this.getNotes())
        }
        vcf += String.format("REV:%s\n", sdf.format(Date()))
        vcf += "END:VCARD"
        return vcf
    }
}
