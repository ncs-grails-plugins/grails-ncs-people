package edu.umn.ncs

/**
This class assigns a particular contact role between two people.
Example contact types that can be created are: Emergency, Tracing, Next of Kin, Power of Attorney...
While this is the class that holds the actual contact relationship between two people, the class
that defines the type of relationship is the edu.umn.ncs.ContactRoleType class.
*/
class ContactRole implements Serializable {

	/**
	This defines the type of contact role that this instance represents.
	The type is restricted to those provided by the ContactRoleType class.
	*/
	ContactRoleType role

	/**
	This is the person acting as the role for the person to whom this ContactRole belongs to.
	Written as an english sentence, it would read:
	<pre>${relatedPerson} is the ${role} contact for ${person}.</pre>
	*/
	Person	relatedPerson

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
	This should containt the date that we gained knowledge of this contact.
	Typically this is the day of, or a few days prior to the date it was entered.
	*/
	Date			infoDate
	/**
	If the source of this information is known, a linked instance of a edu.umn.ncs.Source domain class
	can be assigned telling us where we learned about this particular contact.
	*/
	Source			infoSource

	/**
	The static belongsTo exposes the 'person' attribute for this class that
	the ContactRole belongs to.  This is a required attribute, as it is part of the 'belongsTo' map
	*/
	static belongsTo = [ person : Person ]

	/** This is the default string converter for this domain class.
	This toString method returns the following:
	<pre>"${relatedPerson} is a(n) ${role} contact for ${person}"</pre>
	*/
	String toString() {
		"${relatedPerson} is a(n) ${role} contact for ${person}"
	}

	/**
	This contains any constraints for this domain class.
	Non-default constraints for this class are <ul>
		<li>lastUpdated is nullable (optional)</li>
		<li>userUpaded is nullable (optional)</li>
		<li>infoDate is nullable (optional)</li>
		<li>infoSource is nullable (optional)</li>
	</ul>
	*/
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

	// TODO: Write integration test to make sure that we can sort on another domain class
	/**
		This mapping defines the default sort order of this domain class to be by relatedPerson
	*/
    static mapping = {
		sort 'relatedPerson'
    }
}
