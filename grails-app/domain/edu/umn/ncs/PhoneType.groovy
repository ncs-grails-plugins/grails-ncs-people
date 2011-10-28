package edu.umn.ncs

/**
This domain class defines the different types of phone numbers
that are associated with a PhoneNumber instance.
*/
class PhoneType implements Serializable {

	/**
	The textual representation of the AddressType instance:
	for example "home", "work", "cabin", etc...
	The maximum length for this string is 16 characters.
	*/
    String name

	/** A flag that says whether or not his type of phone
	number can be tied to an address. For example home 
	and work phones can be tied to addresses, but cell 
	phones cannot.
	*/
    boolean allowAddressLink
	
	/**
	The toString() method for this domain class.
	It simply returns the name string.
	*/
    String toString() {
        name
    }

	/**
	The field constraints applied to this domain
	class.  Non default constraints are:
	<ul>
		<li>name, maximum length is 20 characters</li>
	</ul>
	*/
    static constraints = {
    	name(maxSize:20)
    	allowAddressLink()
    }

	/**
		This mapping defines the default sort order of this domain class to be by name
	*/
    static mapping = {
		sort 'name'
    }
}
