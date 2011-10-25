package edu.umn.ncs

/**
This domain class is used to track any alternate last names
that anyone may have.
*/
class LastName implements Serializable {

	/**
	This is the actual last name.
	Maximum length is 30 characters.
	*/
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
