package edu.umn.ncs

// This should pull the name description from the
// i18n file for proper internationalization
// support.
/**
This domain class defines the genders available for
assignment to a particular individual.
Most studies only need: male, femail, unknown.
Others may need transgender information depending on what they are studying (prostate, etc...)
*/
class Gender implements Serializable {

	/**
	<p>The textual representation of the AddressType instance:
	for example: male, female, not given.
	<p>The maximum length for this string is 7 characters.</p>
	*/
	String name
	
	/**
	The toString() method for this domain class.
	It simply returns the name string.
	*/
	String toString() { name }
	
	/**
	The field constraints applied to this domain
	class.  Non default constraints are:
	<ul>
		<li>name, maximum length is 16 characters</li>
	</ul>
	*/
    static constraints = {
    	name(maxSize: 16)
    }

	/**
		This mapping defines the default sort order of this domain class to be by name
	*/
    static mapping = {
		sort 'name'
    }
}
