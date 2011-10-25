package edu.umn.ncs

/**
This domain class represents a country somewhere on this planet earth.
Unfortunatly, due primarily to war, this changes often.  Please keep this up to date.
The easiest way is to pull this data from:
	http://en.wikipedia.org/wiki/Country_code_top-level_domain
*/
class Country implements Serializable {

	/**
	This is the standard international two-letter abbreviaiton for this country.
	For example, 'United States' would be 'us', and 'Spain' would be 'es'.
	*/
	String abbreviation

	/**
	This is the plain english name for this country.
	For example: 'United States'
	*/
	String name
	
	/**
	This is the method that is the default converter of this class to a string.
	This toString() method just returns the contents of the 'name' attribute
	*/
	String toString() {
		name
	}
	
	/**
	This contains any constraints for the domain class.
	Non-default constraints for this class are <ul>
		<li>the abbreviation is limited to 2 characters</li>
		<li>the name is limited to a maximum of 45 characters</li>
	</ul>
	*/
    static constraints = {
		abbreviation(maxSize: 2)
		name(maxSize: 45)
    }

	/**
		This mapping defines the default sort order of this domain class to be by name
	*/
    static mapping = {
		sort 'name'
    }
}
