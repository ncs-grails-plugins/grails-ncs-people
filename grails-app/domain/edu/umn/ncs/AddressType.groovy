package edu.umn.ncs

/**
This domain class defines the different types of mailing address
that are associated with a StreetAddress instance.
*/
class AddressType implements Serializable {

	/**
	The textual representation of the AddressType instance:
	for example "home", "work", "cabin", etc...
	The maximum length for this string is 16 characters.
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
    	name(maxSize:16)
    }

	/**
		This mapping defines the default sort order of this domain class to be by name
	*/
    static mapping = {
		sort 'name'
    }
}

