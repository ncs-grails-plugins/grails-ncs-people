package edu.umn.ncs

class LastName implements Serializable {

	String		lastName
	boolean		isMaiden
	boolean		isFiction
	boolean		isTransaction

	// BEGIN PROVENANCE FIELDS
	Date		dateCreated = new Date()
	String		userCreated = 'unknown'
	String		appCreated
	Date		lastUpdated
	String		userUpdated
	// END PROVENANCE FIELDS

	static belongsTo = [ person : Person ]
	
    static constraints = {
		lastName(maxSize: 30)
		isMaiden()
		isFiction()
		isTransaction()
		createdByWhom(maxSize: 16)
		createdByWhat(maxSize: 30)
		createdWhen()
    }
}
