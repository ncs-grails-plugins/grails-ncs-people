package edu.umn.ncs

class Source implements Serializable {

	String	name
	int 	sid
	boolean	selectable

	// BEGIN PROVENANCE FIELDS
	Date		dateCreated = new Date()
	String		userCreated = 'unknown'
	String		appCreated
	Date		lastUpdated
	String		userUpdated
	// END PROVENANCE FIELDS

    static constraints = {
		name(maxSize:50)
		sid(nullable:true)

		// BEGIN PROVENANCE FIELDS
		dateCreated()
		userCreated()
		appCreated()
		lastUpdated(nullable:true)
		userUpdated(nullable:true)
		// END PROVENANCE FIELDS
	}
}
