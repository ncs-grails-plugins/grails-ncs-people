package edu.umn.ncs

class EmailAddress implements Serializable {

	String		emailAddress
	Date		verifiedDate
	Date		bounceDate

	// BEGIN PROVENANCE FIELDS
	Date		dateCreated = new Date()
	String		userCreated = 'unknown'
	String		appCreated
	Date		lastUpdated
	String		userUpdated
	// END PROVENANCE FIELDS
	
    static constraints = {
		emailAddress(email:true, maxSize: 196)
		verifiedDate(nullable: true)
		bounceDate(nullable: true)
		
		// BEGIN PROVENANCE FIELDS
		dateCreated()
		userCreated()
		appCreated()
		lastUpdated(nullable:true)
		userUpdated(nullable:true)
		// END PROVENANCE FIELDS
	}
}
