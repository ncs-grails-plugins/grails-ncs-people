package edu.umn.ncs

/** This is an implementation of the PersonContact class to tie
a email address to a person.
*/
class PersonEmail extends PersonContact implements Serializable {
	/**
	this is the email address to which this person contact is linking to
	*/
	EmailAddress    emailAddress
	/**
	This is the type of email address that is being linked to this person.
	Examples of address types could be work, home, cabin, P.O. Box, etc...
	*/
	EmailType       emailType

	/** The email address cascades saves and updates automatically */
	static mapping = {
		emailAddress cascade:"save-update"
	}
}

