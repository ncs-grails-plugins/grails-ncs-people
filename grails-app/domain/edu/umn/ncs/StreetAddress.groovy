package edu.umn.ncs

/** This class represents an address.  This can
be a deliverable, or non-deliverable address.
This supports international addresses.
*/
class StreetAddress implements Serializable {

	/** This is the street address line */
    String		address
	/** This is an additional street address 
	 line if needed, but shouldn't be necessary.
	 Avoid using this if possible. */
    String		address2
	/** This is the proper city name.
	Do not abbreviate here, if the city is
	Saint Paul, do NOT use St. Paul.
	*/
    String		city
	/** If this is a US or CA address, this
	is the two-letter abbreviation for the state
	or province. */
    String		state
	/** If this is a US address, this is the 5 digit
	zip code for the address */
    int			zipCode
	/** If this is a US address, this is the 4 digit
	zip4 extension for the zip code if the address
	has been cleaned and standardized. */
    int			zip4
	/** This is the county of the address if needed */
    String		county
	/** This is the country the address resides in */
    Country		country
	/** If this is a Non-US address, this is the 
	international postal delivery code for the address.
	Some countries do not use this, Canada does. */
    String		internationalPostalCode
	/** This flags whether or not USPS mail can be
	delivered here.  Some US addresses must be delivered
	to a P.O. Box rather than the actual address of the
	house. */
    boolean		uspsDeliverable = true

	// BEGIN PROVENANCE FIELDS
	/**
	This field is automatically assigned the date that this particular ContactRole instance was created
	*/
	Date		dateCreated = new Date()
	/**
	This field should be assigned the username of the authenticated user that is creating an instance of this class.
	*/
	String		userCreated = 'unknown'
	/**
	This field should be assigned the name of the application that is creating an instance of this class. 
	*/
	String		appCreated
	/**
	This field should be propogated with the current date and time "new Date()" that an instance of this class is updated.
	Grails will automatically take care of this per section 5.5.1 of the Grails 1.3.7 documentation.
	*/
	Date		lastUpdated
	/**
	This field should be propogated with the username of the authenticated user when an instance of this class is updated.
	*/
	String		userUpdated
	// END PROVENANCE FIELDS
	
	/** This flags whether or not this address has
	been cleaned an standardized.  Do NOT set this 
	to true unless this address has in fact been 
	processed by an address standardization program
	such as Semaphore ZP4, or StrikeIron. */
    boolean		standardized = false

	/** This is an output formating helper function
	that returns the City + State + Zipcode line
	for a mailing address.  This is commonly the bottom
	line on a US postal delivery address. */
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

	/** This is the transient used by this class.  See
	getCityStateZip() for details. */
    static transients = [ 'cityStateZip' ]
	

	/**
	This contains any constraints for the domain class.
	Non-default constraints for this class are
	<dl>
		<dt>address</dt>
		<dd>this can be no longer than 64 characters, the 
		<a href="http://pe.usps.com/text/pub28/28c3_005.html">USPS 
		guidelines</a> state 40 characters as the max.</dd>
		<dt>address2</dt>
		<dd>this is optional (nullable), and can be no longer 
		than 64 characters, the 
		<a href="http://pe.usps.com/text/pub28/28c3_005.html">USPS 
		guidelines</a> state 40 characters as the max.</dd>
		<dt>city</dt>
		<dd>this is optional (nullable), and has a maximum length of 30 characters</dd>
		<dt>state</dt>
		<dd>this is optional (nullable), and has a maximum length of 2 characters</dd>
		<dt>zipCode</dt>
		<dd>this is optional (nullable), and has an allowable range of 00000-99999</dd>
		<dt>zip4</dt>
		<dd>this is optional (nullable), and has an allowable range of 0000-9999</dd>
		<dt>county</dt>
		<dd>this is optional (nullable)</dd>
		<dt>country</dt>
		<dd>this is optional (nullable)</dd>
		<dt>internationalPostalCode</dt>
		<dd>this is optional (nullable), and has a maximum length of 16 characters</dd>
		<dt>userCreated</dt>
		<dd>16 characters</dd>
		<dt>appCreated</dt>
		<dd>the attribute can be no longer than 50 characters</dd>
	</dl>
	*/
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
