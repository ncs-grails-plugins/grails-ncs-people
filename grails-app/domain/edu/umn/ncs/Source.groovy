package edu.umn.ncs

/**
This class defines a source of information for
a particular data element.
*/
class Source implements Serializable {

	/** this is the name of the source, it can be up to 50 characters */
	String	name

	/** this can be the tracked item id associated with the source 
	if available. The TrackedItem is found in the ncs-tracking plugin. */
	int 	itemId

	/** This flags whether or not this source is selectable, and shows
	up on pull-down menus, or auto-complete forms */
	boolean	selectable

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
	These are the GORM constraints applied to this domain class.
	Non-default constraints are as follows:
	<dl>
		<dt>name</dt>
		<dd>Maximum length is 50 characters.</dd>
		<dt>itemId</dt>
		<dd>optional field (nullable)</dd>
		<dt>lastUpdated</dt>
		<dd>optional field (nullable)</dd>
		<dt>userUpdated</dt>
		<dd>optional field (nullable)</dd>
	</dl>
	*/
    static constraints = {
		name(maxSize:50)
		itemId(nullable:true)

		// BEGIN PROVENANCE FIELDS
		dateCreated()
		userCreated()
		appCreated()
		lastUpdated(nullable:true)
		userUpdated(nullable:true)
		// END PROVENANCE FIELDS
	}

	/**
		This mapping defines the default sort order of this domain class to be by name
	*/
    static mapping = {
		sort 'name'
    }
}
