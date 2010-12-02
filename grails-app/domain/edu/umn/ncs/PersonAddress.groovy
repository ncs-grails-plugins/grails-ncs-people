package edu.umn.ncs

class PersonAddress extends PersonContact implements Serializable {
	StreetAddress	streetAddress
	AddressType	addressType
	
	static mapping = {
		streetAddress cascade:"save-update"
	}
}
