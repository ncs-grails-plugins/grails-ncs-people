package edu.umn.ncs

/**
This domain class is used to classify the types of study enrollment as used by the edu.umn.ncs.Subject class.
Example types could be: control, intervention, etc...
*/
class EnrollmentType implements Serializable {

	/**
	This is the english name of the enrollment type associated with a Subject record
	It is limited to a terse 16 characters.
	*/
	String name

	/**
	This is the method that is the default converter of this class to a string.
	This toString() method just returns the contents of the 'name' attribute
	*/
	String toString() { name }

	/**
	This contains any constraints for the domain class.
	Non-default constraints for this class are <ul>
		<li>name can not be longer than 16 characters</li>
	</ul>
	*/
    static constraints = {
    	name(maxSize:16)
	}
}
