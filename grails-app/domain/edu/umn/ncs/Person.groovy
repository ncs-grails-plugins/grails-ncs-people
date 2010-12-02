package edu.umn.ncs

class Person implements Serializable {

    String		title
    String		firstName
    String		middleName
    String		lastName
    String		suffix
    String		name
    Date		birthDate = null
    Date                deathDate = null
    Gender		gender
    Boolean		alive = true
    String		ssn
    Boolean		isRecruitable
	
    // BEGIN PROVENANCE FIELDS
    Date		dateCreated = new Date()
    String		userCreated = 'unknown'
    String		appCreated
    Date		lastUpdated
    String		userUpdated
    // END PROVENANCE FIELDS

    static belongsTo = Household

    static hasMany = [
        households: Household,
        ethnicities: Ethnicity,
        alternateLastNames: LastName,
        streetAddresses: PersonAddress,
        phoneNumbers: PersonPhone,
        emailAddresses: PersonEmail,
        comments: CommentPerson ]

    static transients = [ 'fullName', 'age', 'primaryAddress', 'bestAddress', 'primaryPhone', 'primaryEmail' ]

    String toString() {
        return "Person ID: " + id + ", " + getFullName()
    }

    StreetAddress getPrimaryAddress() {
        // assuming address order # 1 is the primary..
        // this is a big assumtion.  (It assumes all the software is behaving)
        streetAddresses.find{ it.preferredOrder == 1 }?.streetAddress
    }

    StreetAddress getBestAddress() {
        // assuming address order # 1 is the primary..
        // this is a big assumtion.  (It assumes all the software is behaving)

        // let's find all the active addresses, and if they are seasonal,
        // they need to be active within their seasonal range
        def validAddresses = streetAddresses.find{ it.isActive() && it.isSeasonalActive()}
        .sort(! it.isSeasonal(), it.preferredOrder )  // then we sort it, by seasonal


        if (validAddresses) {
            return validAddresses[0]
        } else {
            return null
        }

    }

    PhoneNumber getPrimaryPhone() {
        def validPhone = phoneNumbers.find{it.isActive() && it.isSeasonalActive()}
        .sort(! it.isSeasonal(), it.preferredOrder )

        if (validPhone) {
            return validPhone[0]
        } else return null
    }

    PhoneNumber getPrimaryEmail() {
        def validEmail = emailAddresses.find{it.isActive() && it.isSeasonalActive()}
        .sort(! it.isSeasonal(), it.preferredOrder )

        if (validEmail) {
            return validEmail[0]
        } else return null
    }

    String getFullName() {
        String retString = ""
        if (title != null) {
            retString += title + ". "
        }
        if (firstName != null) {
            retString += firstName
        }
        if (middleName != null) {
            if (firstName != null) {
                if (firstName.length() == 1) {
                    retString += " " + middleName + "."
                } else {
                    retString += " " + middleName.substring(0,1) + "."
                }
            }
        }
        if (lastName != null) {
            retString += " " + lastName
        }
        if (suffix != null) {
            retString += " " + suffix
        }
        return retString.trim().replace("  ", " ")
    }

    Integer getAge() {
        Calendar today = Calendar.getInstance()
        Calendar reference = Calendar.getInstance()

        reference.setTime(birthDate)
        Integer age = today.get(Calendar.YEAR) - reference.get(Calendar.YEAR)
        reference.add(Calendar.YEAR, age);

        if (today.before(reference)) { age-- }

        return age;
    }

    static constraints = {
        title(nullable:true, maxSize:10)
        firstName(nullable:true, maxSize:30)
        middleName(nullable:true, maxSize:20)
        lastName(maxSize:30)
        suffix(nullable:true, maxSize:10)
        name(nullable: true, maxSize:104)
        birthDate(nullable:true)
        deathDate(nullable:true)
        gender(nullable:true)
        alive()
        ssn(nullable:true, maxSize:9)
        isRecruitable()

        // BEGIN PROVENANCE FIELDS
        dateCreated()
        userCreated()
        appCreated()
        lastUpdated(nullable:true)
        userUpdated(nullable:true)
        // END PROVENANCE FIELDS
    }
}