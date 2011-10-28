package edu.umn.ncs

/**
This class represents a subject enrolled in a particular study or substudy.
Once a person is considered 'enrolled' by the study protocol, a Subject
record should be created for that person.
*/
class Subject implements Serializable {

	/**
	This field is the cross-site, central ID assigned to the subject.
	As this ID is often a string containing check-digits, and is not controlled
	by the managing study center, this is a separate ID from the 'id' attribute.
	Common formats seen are: 05-12345-6, 5012345-6, 001234501, MN-0123456, etc...
	*/
	String subjectId 
	/**
	This is the person that is tied to this subject record.
	Oner person can have more than one Subject instances if they are enrolled in 
	more than one study/sub-study, but each Subject record ties to only one
	subject, and in turn is a 1:N relationship
	*/
	Person person

	/**
	This is the study that this subject is associated with.
	*/
	Study study

	/**
	This boolean is used in case a study assigned a subjectId
	before the subject is offially enrolled in the study.
	*/
	boolean randomized = false

	/**
	This is the date that the subject was "officially" enrolled in the
	study.  Often it is referred to as the "randomization date" or
	"enrollment date".
	*/
	Date selectionDate = new Date()

	/**
	This defines the enrollment type for the subject as most
	studies have at least a control and an intervention group.
	This is a 1:N relationship as you can have a 1-N arm study,
	in fact three arm studies are rather common.
	*/
	EnrollmentType enrollment // (control, intervention, high, low, etc...)

	/** This option provides the subject to be enrolled in sevaral sub-groups */
	static hasMany = [ subEnrollments : EnrollmentType ]

	// BEGIN PROVENANCE FIELDS
	/**
	This field is automatically assigned the date that this particular ContactRole instance was created
	*/
	Date		dateCreated = new Date()
	/**
	This field should be assigned the username of the authenticated user that is creating an instance of this class.
	*/
	String		userCreated = 'unknown'
	/**
	This field should be assigned the name of the application that is creating an instance of this class. 
	*/
	String		appCreated
	/**
	This field should be propogated with the current date and time "new Date()" that an instance of this class is updated.
	Grails will automatically take care of this per section 5.5.1 of the Grails 1.3.7 documentation.
	*/
	Date		lastUpdated
	/**
	This field should be propogated with the username of the authenticated user when an instance of this class is updated.
	*/
	String		userUpdated
	// END PROVENANCE FIELDS

	/**
	This is the default String converter method for this class.
	This method returns the following:
	<pre>"${study}, ${subjectId}"</pre>
	*/
	String toString() {
		"${study}:${subjectId}"
	}

	/**
	This contains any constraints for this domain class.
	Non-default constraints for this class are <ul>
		<li>subjectId is limited to 16 characters</li>
		<li>lastUpdated is nullable (optional)</li>
		<li>userUpaded is nullable (optional)</li>
	</ul>
	*/
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

	/**
		This mapping defines the default sort order of this domain class to be by subjectId
	*/
    static mapping = {
		sort 'subjectId'
    }
}
