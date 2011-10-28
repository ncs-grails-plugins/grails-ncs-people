package edu.umn.ncs

/** This class represents a phone number
in the system.  It supports any valid phone
number on the planet, including country codes
and extensions. It also provides string
formating functions for prettier output. */
class PhoneNumber implements Serializable {

	/** The country code for this phone number.
	This defaults to 1, which is the code for 
	the United States. */
    int			countryCode = 1
	/** The phone number itself.  This should 
	not be stored with any punctuation, i.e. 
	dashes, parentheses or dots.
	*/
    String		phoneNumber
	/** If necessary, this is the extension
	associated with this phone number. */
    String		extension

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


	/** The following transients are meta attributes
	for this class, see the get* functions for details. */
    static transients = [ 'prettyPhone' ]

	/** The defailt string converter for this class.
	This returns the output from the getPrettyPhone()
	function.
	*/
	String toString() { prettyPhone }

	/** This formats a phone number, plus it's
	country code, and any extension it may have
	for output as a string.
	*/
    String getPrettyPhone() {
		strip all non-numeric characters out
        def phone = phoneNumber.replaceAll(/[)(.-]/, '')

            if ( phone.size() == 10) {
                phone =  phone.substring(0,3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6)
            } else if(phone.size() == 9) {
                phone =  phone.substring(0,3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6)
            } else if(phone.size() == 8) {
                phone =  phone.substring(0,1) + "-" + phone.substring(1, 4) + "-" + phone.substring(4)
            } else if(phone.size() == 7) {
                phone =  phone.substring(0, 3) + "-" + phone.substring(3)
            } else if(phone.size() == 6) {
                phone =  phone.substring(0,3) + "-" + phone.substring(3)
            } else if(phone.size() == 5) {
                phone =  phone.substring(0,2) + "-" + phone.substring(2)
            }

		if (extension) { phone + ' x' + extension }
        return phone
    }


	/**
	These are the GORM constraints applied to this domain class.
	Non-default constraints are as follows:
	<dl>
		<dt>phoneNumber</dt>
		<dd>Restricted to the following characters: 0-9)(.- . Maximum length is 24 characters.</dd>
		<dt>extension</dt>
		<dd>optional field (nullable), maximum length is 16 characters</dd>
		<dt>lastUpdated</dt>
		<dd>optional field (nullable)</dd>
		<dt>userUpdated</dt>
		<dd>optional field (nullable)</dd>
	</dl>
	*/
    static constraints = {
        countryCode()
        phoneNumber(matches:'[0-9)(.-]+', maxSize:24)
        extension(nullable:true, maxSize:16)
        // BEGIN PROVENANCE FIELDS
        dateCreated()
        userCreated()
        appCreated()
        lastUpdated(nullable:true)
        userUpdated(nullable:true)
        // END PROVENANCE FIELDS
    }

	static mapping {
		sort 'phoneNumber'
	}
}
