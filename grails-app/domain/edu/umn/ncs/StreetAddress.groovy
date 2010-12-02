package edu.umn.ncs

class StreetAddress implements Serializable {

    String		address
    String              address2
    String		city
    String		state
    int			zipCode
    int			zip4
    String		county
    Country		country
    String		internationalPostalCode
    boolean		uspsDeliverable = true

    // BEGIN PROVENANCE FIELDS
    Date		dateCreated = new Date()
    String		userCreated = 'unknown'
    String		appCreated
    Date		lastUpdated
    String		userUpdated
    // END PROVENANCE FIELDS
	
    boolean		standardized = false

    String getCityStateZip() {
        String cityStateZip = ""

        if (city) {
            cityStateZip += city
            if (state) {
                cityStateZip += ', '
            }
        }
        if (state) { cityStateZip += state }

        if (country.abbreviation.toLowerCase() == 'us') {
            // US Address
            if (zipCode)  {
                cityStateZip += ' ' + String.format('%05d', zipCode)
                if (zip4) {
                    cityStateZip += "-" + String.format('%04d', zip4)
                }
            }
        } else {
            // International Address
            if (internationalPostalCode) {
                cityStateZip += ' ' + internationalPostalCode
                if (country) {
                    cityStateZip += ', ' + country.name
                }
            }
        }
        return cityStateZip.replaceAll('  ', ' ')
    }

    static transients = [ 'cityStateZip' ]
	
    static constraints = {
        address(maxSize:64)
        address2(nullable: true, maxSize:64)
        city(nullable: true, maxSize:30)
        state(nullable: true, maxSize:2)
        zipCode(nullable: true, max:99999, min:0)
        zip4(nullable: true, max:9999, min:0)
        county(nullable: true)
        country(nullable: true)
        internationalPostalCode(nullable: true, maxSize:16)
        uspsDeliverable()

        // BEGIN PROVENANCE FIELDS
        dateCreated()
        userCreated()
        appCreated()
        lastUpdated(nullable:true)
        userUpdated(nullable:true)
        // END PROVENANCE FIELDS
    }
}
