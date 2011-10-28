package edu.umn.ncs
// NOTE: (buffer # 15, see from #9)
class LastName implements Serializable {

	String		lastName

	/**
	This defines whether or not this name is a maiden name
	*/
	boolean		isMaiden
	/**
	This defines whether or not this name is a made up name (hyphenated) used for matching purposes only
	*/
	boolean		isFiction
	/**
	This defines whether or not this last name is from a transacted out old last name
	*/
	boolean		isTransaction

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
	The static belongsTo exposes the 'person' attribute for this class that
	the comment belongs to.  This is a required attribute, as it is part of the 'belongsTo' map
	*/
	static belongsTo = [ person : Person ]
	
	/**
	This contains any constraints for this domain class.
	Non-default constraints for this class are <ul>
		<li>lastName can have a maximum length of 30 characters </li>
		<li>lastUpdated is nullable (optional)</li>
		<li>userUpaded is nullable (optional)</li>
		<li>userCreated has a maximum length of 16 characters</li>
		<li>appCreated has a maximum length of 30 characters</li>
	</ul>
	*/
    static constraints = {
		lastName(maxSize: 30)
		isMaiden()
		isFiction()
		isTransaction()
		dateCreated()
		lastUpdated(nullable:true)
		userUpdated(nullable:true)
		userCreated(maxSize: 16)
		appCreated(maxSize: 30)
		createdWhen()
    }
	static mapping = {
		sort 'lastName'
	}
}
