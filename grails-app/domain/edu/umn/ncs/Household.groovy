package edu.umn.ncs

class Household implements Serializable {

    String name
    static belongsTo = [ dwelling: DwellingUnit ]
    static hasMany = [ people: Person ]
	
    // BEGIN PROVENANCE FIELDS
    Date		dateCreated = new Date()
    String		userCreated = 'unknown'
    String		appCreated
    Date		lastUpdated
    String		userUpdated
    // END PROVENANCE FIELDS

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
}
