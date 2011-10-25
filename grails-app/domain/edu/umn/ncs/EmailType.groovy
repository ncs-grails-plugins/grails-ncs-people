package edu.umn.ncs

/**
This domain class is used to classify the types of email addresses.
Example types could be: work, home, list, etc...
*/
class EmailType implements Serializable {

	/**
	This is the english name of the email type associated with an PersonEmail contact point.
	It is limited to a terse 16 characters.
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
		<li>name can not be longer than 16 characters</li>
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
