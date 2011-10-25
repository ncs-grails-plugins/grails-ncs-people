package edu.umn.ncs

/**
This class represents a single dwelling unit, commonly referred to as a deliverable address.
Because addresses sometimes change; duplexes become single 
family homes, street numbers changes, etc..., this it 
tracked separately from a street address, however it ties to a 
StreetAddress in a 1:1 relationship.
*/
class DwellingUnit implements Serializable {

	/**
	This is an optional name to assign to a dwelling unit.
	Examples could include: "upstairs unit", "zirbes residence", "le chateau", etc....
	*/
    String name

	/**
	This is the StreetAddress that this dwelling unit is tied to.
	Example: 200 Oak St SE, Minneapolis, MN 55455
	*/
    StreetAddress address
	
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
	This hasMany maps many Household instances to households colleciton
	as a single dwelling unit may have once or more households.
	For instance, a single dwelling unit may contain more than one
	nuclear family if someone is renting out a room or a basement.
	*/
    static hasMany = [ households : Household ]

	/**
	This is the default String converter method for this class.
	This method returns the following:
	<pre>"Dwelling Unit #${id}, ${address.address}"</pre>
	*/
	String toString() {
		"Dwelling Unit #${id}, ${address.address}"
	}

	/**
	This contains any constraints for this domain class.
	Non-default constraints for this class are <ul>
		<li>name is nullable (optional)</li>
		<li>lastUpdated is nullable (optional)</li>
		<li>userUpaded is nullable (optional)</li>
	</ul>
	*/
    static constraints = {
        name(nullable: true)

        // BEGIN PROVENANCE FIELDS
        dateCreated()
        userCreated()
        appCreated()
        lastUpdated(nullable:true)
        userUpdated(nullable:true)
        // END PROVENANCE FIELDS
    }

	/**
		This mapping defines the default sort order of this domain class to be by streetAddress
	*/
    static mapping = {
		sort 'streetAddress'
    }
}
