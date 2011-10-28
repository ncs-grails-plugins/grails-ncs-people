package edu.umn.ncs

/**
This class represents a single email address
along with all of the required tracking fields 
associated with the entity.
This is usually tied to a PersonContact record
through the PersonEmail class that extends the PersonContact
domain class.
*/
class EmailAddress implements Serializable {

	/**
	This is the actual email address that this domain class is tied to.
	*/
	String		emailAddress

	/**
	This is the (optional) date that the some one (or something) 
	verified that this email address actually worked.
	*/
	Date		verifiedDate
	/**
	This is the date (optional), that hopefully never gets filled, when the email address bounced last.
	This date is for use within an application, and is not automatically propogated.
	*/
	Date		bounceDate

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
	 This contains any constraints for this domain class.
	 Non-default constraints for this class are <ul>
	 	<li>emailAddress must be a valid email address according to <a href="http://tools.ietf.org/html/rfc2822">RFC 2822</a>.</li>
		<li>verifiedDate is nullable (optional)</li>
		<li>bounceDate is nullable (optional)</li>
		<li>lastUpdated is nullable (optional)</li>
		<li>userUpaded is nullable (optional)</li>
	</ul>
	*/
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

	/**
		This mapping defines the default sort order of this domain class to be by emailAddress
	*/
    static mapping = {
		sort 'emailAddress'
    }
}
