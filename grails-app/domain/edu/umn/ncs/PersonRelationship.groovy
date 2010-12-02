package edu.umn.ncs

// Person <-> Person relationship
class PersonRelationship extends PersonContact implements Serializable {
	Person	relatedPerson
	RelationshipType	relation

	static mapping = {
		relatedPerson cascade:"save-update"
	}
}
