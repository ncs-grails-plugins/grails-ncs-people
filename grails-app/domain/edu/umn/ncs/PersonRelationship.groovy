package edu.umn.ncs

/** This is an implementation of the PersonContact class to tie
another person to a person.
*/
class PersonRelationship extends PersonContact implements Serializable {
	/**
	this is the other person to which this person  has a relationship with
	*/
	Person	relatedPerson
	/**
	This is the type of relationship that is being linked to this relationship.
	Examples of address types could be work, home, cabin, P.O. Box, etc...
	*/
	RelationshipType	relation

	/** The person relationship cascades saves and updates automatically */
	static mapping = {
		relatedPerson cascade:"save-update"
	}
}
