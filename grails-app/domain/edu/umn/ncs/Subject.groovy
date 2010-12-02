package edu.umn.ncs

class Subject implements Serializable {

	String subjectId 
	Person person
	Study study
	boolean randomized = false
	Date selectionDate = new Date()
	EnrollmentType enrollment // (control, intervention, high, low, etc...)

	// BEGIN PROVENANCE FIELDS
	Date		dateCreated = new Date()
	String		userCreated = 'unknown'
	String		appCreated
	Date		lastUpdated
	String		userUpdated
	// END PROVENANCE FIELDS

	String toString() {
		study.toString() + ' : ' + subjectId
	}

    static constraints = {
		subjectId(maxSize: 16)
		person()
		study()
		randomized()
		selectionDate()
		enrollment()

		// BEGIN PROVENANCE FIELDS
		dateCreated()
		userCreated()
		appCreated()
		lastUpdated(nullable:true)
		userUpdated(nullable:true)
		// END PROVENANCE FIELDS
    }
}
