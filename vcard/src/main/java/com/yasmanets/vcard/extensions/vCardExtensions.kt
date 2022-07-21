package com.yasmanets.vcard.extensions

import com.yasmanets.vcard.Address
import com.yasmanets.vcard.VCard

/**
 *   Created by Yaser El Dabete Arribas
 *   Date: 19/7/22
 */

/**
 * Returns the vCard data from the given [String].
 */
fun VCard.getDataFromVCF(vcf: String): VCard {
	var lines = vcf.split("\n").toMutableList()
	lines = removeUnusedLines(lines)

	val urls: MutableList<String> = mutableListOf()
	val labels: MutableList<String> = mutableListOf()

	for (line in lines) {
		if (line.contains("VERSION")) {
			when (line.split(":")[1]) {
				VCard.VERSION.V3.value -> this.setVersion(VCard.VERSION.V3)
				else -> this.setVersion(VCard.VERSION.V3)
			}
			continue
		}

		if (line.contains("FN;")) {
			val values = line.split(":")[0].split(";")
			when (values[1]) {
				VCard.ENCODING.UTF8.value -> this.setEncoding(VCard.ENCODING.UTF8)
				VCard.ENCODING.ISO.value -> this.setEncoding(VCard.ENCODING.ISO)
				else -> this.setEncoding(VCard.ENCODING.UTF8)
			}
			continue
		}

		if (line.contains("N;")) {
			val values = line.split(":")[1].split(";")
			this.setSurname(values[0])
			this.setName(values[1])
			continue
		}

		if (line.contains("PHOTO")) {
			val photo = line.split(":")[1]
			val values = line.split(":")[0].split(";")
			val format = when (values[values.size - 1]) {
				VCard.FORMAT.PNG.value -> VCard.FORMAT.PNG
				VCard.FORMAT.JPEG.value -> VCard.FORMAT.JPEG
				VCard.FORMAT.JPG.value -> VCard.FORMAT.JPG
				else -> VCard.FORMAT.PNG
			}
			this.setPhoto(format, photo)
			continue
		}

		if (line.contains("EMAIL")) {
			val values = line.split(":")
			this.setEmail(values[1])
			continue
		}

		if (line.contains("TEL")) {
			val values = line.split(";")
			val phone = values[1].split(":")[1]

			var type = VCard.TYPE.WORK
			if (values[1].contains(VCard.TYPE.HOME.value)) {
				type = VCard.TYPE.HOME
			}
			this.setPhone(type, phone)
			continue
		}

		if (line.contains("TITLE")) {
			val title = line.split(":")[1]
			this.setTitle(title)
			continue
		}

		if (line.contains("ROLE")) {
			val role = line.split(":")[1]
			this.setRole(role)
			continue
		}

		if (line.contains("ORG")) {
			val organization = line.split(":")[1]
			this.setOrganization(organization)
			continue
		}

		if (line.contains("URL") && line.contains("TYPE=WORK")) {
			val organizationUrl = line.slice(line.indexOf(":", 0) + 1 until line.length)
			this.setOrganizationUrl(organizationUrl)
			continue
		}

		if (line.contains("ADR")) {
			val values = line.split(":")
			val firstValues = values[0].split(";")
			val addressValues = values[1].split(";").filter { it.isNotEmpty() }

			val type = if (firstValues[firstValues.size - 1] == VCard.TYPE.WORK.value) VCard.TYPE.WORK else VCard.TYPE.HOME
			val address = Address()
			address.setStreet(addressValues[0])
			address.setCity(addressValues[1])
			address.setRegion(addressValues[2])
			address.setPostalCode(addressValues[3])
			address.setCountry(addressValues[4])
			this.setAddress(type, address)
			continue
		}

		if (line.contains("URL")) {
			val url = line.slice(line.indexOf(":", 0) + 1 until line.length)
			urls.add(url)
			continue
		}

		if (line.contains("ABLABEL")) {
			val values = line.split(":")
			labels.add(values[1])
			continue
		}
	}

	if (urls.isNotEmpty()) {
		for (i in 0 until urls.size) {
			val label = if (labels.isNotEmpty() && labels.size > i) labels[i] else i.toString()
			this.setUrl(label, urls[i])
		}
	}
	return this
}

/**
 * Returns the VCF lines without unnecessary data from the given [MutableList].
 */
private fun removeUnusedLines(lines: MutableList<String>): MutableList<String> {
	lines.removeAt(0)
	lines.removeAt(lines.size - 1)
	lines.removeAt(lines.size - 1)
	return lines
}