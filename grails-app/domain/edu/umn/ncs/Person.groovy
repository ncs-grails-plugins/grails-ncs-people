package edu.umn.ncs

/**
This class represents a person in the system
all people, no matter their role in the system, get
a record in this table.
All people in this database are treated as "first 
class citizens" this means that a person who is a subject
can have all the same addressing, contact, and 
relationship features of someone in the system that
is only there as an emergency contact.
*/
class Person implements Serializable {

	/** This is the person's proper title. 
	This field can hold up to 10 characters.
	Examples of titles are Mr, Ms, Dr, Rev, etc... */
    String		title
	/** This is the first name of the person.
	This field can hold up to 30 characters. */
    String		firstName
	/** This field is the person's middle name.
	The maximum length for this field is 20 characters.
	*/
    String		middleName
	/** This is the last name of the person.
	This field can hold up to 30 characters. */
    String		lastName
	/** This is the person's proper title. 
	This field can hold up to 10 characters.
	Examples of titles are Sr, III, etc... */
    String		suffix
	/** This is set to "fullName()" every time
	that the person record is updated. */
	String		name

	/** The birth date of the person if known.
	  If only a portion of the date is unknown, be
	  sure to update the birthDate*Known fields 
	  appropriately.
	*/
    Date		birthDate = null
	/** If the day of the birthDate is unknown,
	please set this to false. */
	Boolean		birthDateDayKnown = true
	/** If the month of the birthDate is unknown,
	please set this to false. */
	Boolean		birthDateMonthKnown = true
	/** If the year of the birthDate is unknown,
	please set this to false. */
	Boolean		birthDateYearKnown = true

	/** The date of death of the person if known.
	  If only a portion of the date is unknown, be
	  sure to update the deathDate*Known fields 
	  appropriately.
	*/
    Date		deathDate = null
	/** If the day of the deathDate is unknown,
	please set this to false. */
	Boolean		deathDateDayKnown = true
	/** If the month of the deathDate is unknown,
	please set this to false. */
	Boolean		deathDateMonthKnown = true
	/** If the year of the deathDate is unknown,
	please set this to false. */
	Boolean		deathDateYearKnown = true

	static belongsTo = Household

	/** The gender, or sex of the person.
	If unknown, set to null */
    Gender		gender
	/** This flags whether or not a person
	is alive.
	If the deathDate variable is cleared, then
	this should be set to true.
	*/
    Boolean		alive = true
	/** This is the SSN of the person, 
	if needed. */
    String		ssn
	/** Use this flag to see if this 
	  participant is eligible for
	  recruitment into a study.
	  Set this to true only if the
	  person might be a study
	  subject.
	*/
    Boolean		isRecruitable
	
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
	This contains all of the foreign key references
	for this class.
	The following attributes are collections of their respective object/
	<dl>
		<dt>households</dt>
		<dd>a collection of edu.umn.ncs.Household objects to 
		whom this person is a member of.</dd>

		<dt>ethnicities</dt>
		<dd>these are all the ethnicities associated with
		this person</dd>

		<dt>alternateLastNames</dt>
		<dd>this is a collection of any alternate last
		names that is associated with this person. It 
		is often  used for things like maiden names.</dd>

		<dt>streetAddresses</dt>
		<dd>this is a collection of all the PersonAddress
		objects that are used to assign an address to 
		a person.</dd>

		<dt>phoneNumbers</dt>
		<dd>this is a collection of all the PersonPhone
		objects that tie a person to a phone number.</dd>

		<dt>emailAddresses</dt>
		<dd>this is a collection of all the PersonEmail
		objects that tie a person to a phone number.</dd>

		<dt>comments</dt>
		<dd>this is a collection of all the PersonComment
		objects that are used to store comments about
		this person.</dd>

	</dl>
	*/
    static hasMany = [
        households: Household,
        ethnicities: Ethnicity,
        alternateLastNames: LastName,
        streetAddresses: PersonAddress,
        phoneNumbers: PersonPhone,
        emailAddresses: PersonEmail,
        comments: CommentPerson ]

	/** The following transients are meta attributes
	for this class, see the get* functions for details. */
    static transients = [ 
		'fullName', 
		'age', 
		'primaryAddress', 
		'bestAddress', 
		'primaryPhone', 
		'primaryEmail' ]

	/** The defailt string converter for this class.
	This returns "Person ID: ${id}, ${fullName}"
	*/
    String toString() {
        return "Person ID: " + id + ", " + getFullName()
    }

	/**
	getPrimaryAddress() returns the primary street address for
	this individual, if available.  It uses the preferredOrder
	attribute of the PersonAddress class to find the #1 address.
	*/
    StreetAddress getPrimaryAddress() {
        // assuming address order # 1 is the primary..
        // this is a big assumtion.  (It assumes all the software is behaving)
        streetAddresses.find{ it.preferredOrder == 1 }?.streetAddress
    }

	/**
	getBestAddress() returns the "best" address for this 
	particular date and time.  If the person has a seasonal
	address, then it puts preference over the address with
	the smallest time interval that is valid for today.  This
	is helpful for "snowbirds" and people with cabins.
	*/
    PersonAddress getBestAddress() {
        // let's find all the active addresses, and if they are seasonal,
        // they need to be active within their seasonal range
        def validAddresses = streetAddresses.findAll{ it.isActive() && it.isSeasonalActive()}
			.sort{ it.preferredOrder }  // then we sort it, by seasonal
			.sort{! it.isSeasonal() }  // then we sort it, by preferred order

        if (validAddresses) {
            return validAddresses[0]
        } else {
            return null
        }

    }

	/**
	getPrimaryPhone() returns the primary phone numberfor
	this individual, if available.  It uses the preferredOrder
	attribute of the PersonPhone class to find the #1 address.
	*/
    PersonPhone getPrimaryPhone() {
        def validPhone = phoneNumbers.findAll{it.isActive() && it.isSeasonalActive()}
			.sort{ it.preferredOrder }  // then we sort it, by seasonal
			.sort{! it.isSeasonal() }  // then we sort it, by preferred order

        if (validPhone) {
            return validPhone[0] 
        } else { return null }
    }

	/**
	getPrimaryEmail() returns the primary email address for
	this individual, if available.  It uses the preferredOrder
	attribute of the PersonAddress class to find the #1 address.
	*/
    PersonEmail getPrimaryEmail() {
        def validEmail = emailAddresses.findAll{it.isActive() && it.isSeasonalActive()}
			.sort{ it.preferredOrder }  // then we sort it, by seasonal
			.sort{! it.isSeasonal() }  // then we sort it, by preferred order

        if (validEmail) {
            return validEmail[0]
        } else { return null }
    }

	/** getFullName returns a single string
	containing the concatenation of the title, firstName,
	middleName, lastName and suffix; using what ever of those
	are available to create as complete of a name as possible.
	*/
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

	/**
	This calculates the age of this person based on today's date and
	thier birthDate field.
	*/
    Integer getAge() {
        Calendar today = Calendar.getInstance()
        Calendar reference = Calendar.getInstance()

        reference.setTime(birthDate)
        Integer age = today.get(Calendar.YEAR) - reference.get(Calendar.YEAR)
        reference.add(Calendar.YEAR, age);

        if (today.before(reference)) { age-- }

        return age;
    }

	/**
	This is the before update trigger for this class, and it will make sure
	that the 'alive' and 'name' name fields are updated appropriately when
	the domain class instance changes.
	*/
	def beforeUpdate() {
		if ( this.isDirty() ) {
			// update alive to dead if necessary
			if ( this.alive && this.deathDate ) {
				this.alive = false
			}

			// save full name
			this.name = this.fullName()
		}
	}

	/**
	These are the GORM constraints applied to this domain class.
	Non-default constraints are as follows:
	<dl>
		<dt>title</dt>
		<dd>optional field (nullable), maximum length is 10 characters</dd>
		<dt>firstName</dt>
		<dd>optional field (nullable), maximum length is 30 characters</dd>
		<dt>middleName</dt>
		<dd>optional field (nullable), maximum length is 20 characters</dd>
		<dt>lastName</dt>
		<dd>optional field (nullable), maximum length is 30 characters</dd>
		<dt>suffix</dt>
		<dd>optional field (nullable), maximum length is 10 characters</dd>
		<dt>birthDate</dt>
		<dd>optional field (nullable)</dd>
		<dt>deathDate</dt>
		<dd>optional field (nullable)</dd>
		<dt>gender</dt>
		<dd>optional field (nullable)</dd>
		<dt>ssn</dt>
		<dd>optional field (nullable), maximum length is 9 characters</dd>
		<dt>lastUpdated</dt>
		<dd>optional field (nullable)</dd>
		<dt>userUpdated</dt>
		<dd>optional field (nullable)</dd>
	</dl>
	*/
    static constraints = {
        title(nullable:true, maxSize:10)
        firstName(nullable:true, maxSize:30)
        middleName(nullable:true, maxSize:20)
        lastName(maxSize:30)
        suffix(nullable:true, maxSize:10)
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

	static mapping = {
		sort lastName:'asc', firstName:'asc', middleName:'asc'
	}
}
