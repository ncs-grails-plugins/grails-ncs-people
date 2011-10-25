package edu.umn.ncs

/**
This class provides a holder for all allowable contact types, and is used by the edu.umn.ncs.ContactRole domain class.
Example contact types are: Emergency, Tracing, Next of Kin, Power of Attorney...
This provides a way to add one person as a contact for another, but describe the contact role that they play so that the person is not contacted for a reason that is not applicable to them.
*/
class ContactRoleType implements Serializable {

	/**
	This is domain class that defines the types of ContactRole instances that can be created.
	The maximum length of the name field is 24 characters, so use them wisely.
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
		<li>name can not be longer than 24 characters</li>
	</ul>
	*/
    static constraints = {
    	name(maxSize: 24)
    }

	/**
		This mapping defines the default sort order of this domain class to be by name
	*/
    static mapping = {
		sort 'name'
    }
}
