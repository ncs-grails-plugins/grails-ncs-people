package edu.umn.ncs

// Allowable contact types
// Emergency, Tracing, Next of Kin, Power of Attorney...
class ContactRole implements Serializable {

	ContactRoleType role
	Person	relatedPerson

		// BEGIN PROVENANCE FIELDS
	Date		dateCreated = new Date()
	String		userCreated = 'unknown'
	String		appCreated
	Date		lastUpdated
	String		userUpdated
	// END PROVENANCE FIELDS

	Date			infoDate
	Source			infoSource

	static belongsTo = [ person : Person ]

    static constraints = {
		role()

		// BEGIN PROVENANCE FIELDS
		dateCreated()
		userCreated()
		appCreated()
		lastUpdated(nullable:true)
		userUpdated(nullable:true)
		// END PROVENANCE FIELDS

		infoDate(nullable: true)
		infoSource(nullable: true)
	}
}
