package edu.umn.ncs

/**
This domain class is used to define a single family unit.
A family unit has one or more people (edu.umn.edu.Person) records associated with it)
It requires that the family unit to be tied to a particular dwelling unit.
   (Time will tell if this works, it is realized that a family unit may be homeless, but it 
   was thought that we can create a transient dwelling unit if needed)
Please note that a person can be defined as having multiple mailing address locations, so the 
dwellin unit tie should NOT be used to find a mailing address, only for demographic statistics.
*/
class Household implements Serializable {

	/**
	This is the (optional) name of the household.
	Examples could be: "Zirbes household, or Strahan household"
	*/
    String name
	
	/**
	A household must be tied to a dwellingUnit.
	*/
    static belongsTo = [ dwelling: DwellingUnit ]

	/**
	A household may have many people records associated with it.
	*/
    static hasMany = [ people: Person ]
	
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
		<li>name is nullable (optional)</li>
		<li>lastUpdated is nullable (optional)</li>
		<li>userUpaded is nullable (optional)</li>
	</ul>
	*/
    static constraints = {
        name(nullable:true)

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
