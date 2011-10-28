package edu.umn.ncs

/** This is an implementation of the PersonContact class to tie
a street address to a person.
*/
class PersonAddress extends PersonContact implements Serializable {
	/**
	this is the street address to which this person contact is linking to
	*/
	StreetAddress	streetAddress
	/**
	This is the type of street address that is being linked to this person.
	Examples of address types could be work, home, cabin, P.O. Box, etc...
	*/
	AddressType	addressType
	
	/** The person address cascades saves and updates automatically */
	static mapping = {
		streetAddress cascade:"save-update"
	}
}
