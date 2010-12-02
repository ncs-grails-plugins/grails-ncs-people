package edu.umn.ncs

class DwellingUnit implements Serializable {

    String name
    StreetAddress address
	
    // BEGIN PROVENANCE FIELDS
    Date		dateCreated = new Date()
    String		userCreated = 'unknown'
    String		appCreated
    Date		lastUpdated
    String		userUpdated
    // END PROVENANCE FIELDS

    static hasMany = [ households : Household ]

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
}
