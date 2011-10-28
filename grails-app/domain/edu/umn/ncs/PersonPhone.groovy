package edu.umn.ncs

/** This is an implementation of the PersonContact class to tie
a phone number to a person.
*/
class PersonPhone extends PersonContact implements Serializable {
	/**
	this is the phone number to which this person contact is linking to
	*/
	PhoneNumber	phoneNumber
	/**
	This is the type of phone number that is being linked to this person.
	Examples of address types could be work, home, cabin, P.O. Box, etc...
	*/
	PhoneType	phoneType
	
	/** The person email cascades saves and updates automatically */
	static mapping = {
		emailAddress cascade:"save-update"
	}
}

