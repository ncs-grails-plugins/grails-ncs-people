package edu.umn.ncs

// SUPERCLASS
// This class is inherited by many other classes.
// DO NOT USE IT DIRECTLY!

/** This domain class is not used directly, it is inherited by
other classes.  This base class provides the base functionality
needed to track contact relationships from a person to some
other kind of entity.
*/
class PersonContact implements Serializable {

	/**
	This optional field is used if this contact point 
	is through a proxy person, in other
	words, the contact point belongs to someone else, not the
	person whom the contact is tied to, then assign a 
	proxyContact of the proxy edu.umn.ncs.Person here.
	Examples of this usage are "in care of", sometimes
	noted as "% Some Person"
	*/
    Person proxyContact

	/** This is the date that this contact point becomes
	active.  By default, this date is today.
	*/
    Date startDate = new Date()
	/** This is teh last date that this contact point
	is active.  This is useful if someone tells you 
	that they are moving on a certain date, or they will 
	no longer have a certain phone number on a particular
	date */
    Date endDate

	/** If this is a seasonal contact point, this is
	the month of the year that this contact point starts
	to be valid. These are common with "snow bird"
	phone numbers and addresses. This field defaults to 
	the value 0, which makes it inactive.
	*/
    int	startMonth = 0
	/** If this is a seasonal contact point, this is
	the day of month of the year that this contact point 
	starts to be valid. These are common with "snow bird"
	phone numbers and addresses. This field defaults to 
	the value 1.
	*/
    int	startDay = 1
	/** If this is a seasonal contact point, this is
	the month of the year that this contact point ceases
	to be valid. These are common with "snow bird"
	phone numbers and addresses. This field defaults to
   	the value 0, which makes it inactive.
	*/
    int	endMonth = 0
	/** If this is a seasonal contact point, this is
	the day of month of the year that this contact point 
	ceases to be valid. These are common with "snow bird"
	phone numbers and addresses. This field defaults to 
	the value 31.
	*/
    int	endDay = 31

	/**
	This flag tells us if it's OK to use this contact
	point.  For example, sometimes someone will notify 
	us of an address, such as a cabin, but they don't 
	want us to send mail to them there.
	*/
    boolean okToUse = true
	/** This is the preferred order of the contact
	point.  If order is = 1 for a particular contact
	type, then it is the preffered contact point.
	*/
    int preferredOrder = 0

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
	This is the date that you were informed of
	this contact point.
	*/
    Date			infoDate
	/**
	This is the source of information that provided
	you the information regarding this contact point.
	*/
    Source			infoSource

	/**
	This returns the classification of the contact
	point based on what some of the helper functions.
	Possible values it can return are:
	<ul>
		<li>seasonal</li>
		<li>seasonal-inactive</li>
		<li>do-not-use</li>
		<li>inactive</li>
		<li><em>blank</em></li>
	</ul>
	*/
    String getContactClass() {
        def contactClass = ""

        // classification tree...
        if (isActive()) {
            // Active
            if (okToUse) {
                // Mailable
                if (isSeasonal()) {
                    if (isSeasonalActive()) {
                        contactClass = "seasonal"
                    } else {
                        contactClass = "seasonal-inactive"
                    }
                } else {
                    contactClass = ""
                }
            } else {
                contactClass = "do-not-use"
            }
        } else {
            contactClass = "inactive"
        }
        return contactClass
    }

	/**
	This returns the category of this contact
	point based on what some of the helper functions.
	This is used primarily for display purposes.
	Possible values it can return are:
	<ul>
		<li>Seasonal</li>
		<li>Do Not Use</li>
		<li>Inactive</li>
		<li><em>blank</em></li>
	</ul>
	*/
    String getContactCategory() {
        def contactCategory = ""

        // classification tree...
        if (isActive()) {
            // Active
            if (okToUse) {
                // Mailable
                if (isSeasonal()) {
                    contactCategory = "Seasonal"
                } else {
                    contactCategory = ""
                }
            } else {
                contactCategory = "Do Not Use"
            }
        } else {
            contactCategory = "Inactive"
        }
        return contactCategory
    }

	/**
	This function just removes some of the basic class name
	information, leaving the name of the contact type.
	It just removes the string "class edu.umn.ncs.Person"
	from the class name, and returns that.
	*/
    String getContactType() {
        def className = this.class.toString()
        className = className.replace('class edu.umn.ncs.Person', '')
        return className
    }

    /** This functions checks to see if this 
	  contact point is active/valid today.
	*/
    boolean isActive() {
        Date today = new Date()
        def active = true
		
        if (today < startDate) {
            active = false
        } else if (endDate && today > endDate) {
            active = false
        }
        return active
    }

    /** See if this conact point is a "snow bird" address,
	phone, email, etc... */
    boolean isSeasonal() {
        if (startMonth && endMonth) {
            return true
        } else {
            return false
        }
    }


    /** This checks to see if this seasonal contact
	  point is in it's "active" window */
    boolean isSeasonalActive() {
        def seasonalActive = true

        if (startMonth && endMonth && okToUse) {
            def today = new GregorianCalendar()
            // Java Month is zero-based
            def curMonth = today.get(Calendar.MONTH) + 1
            def curDay = today.get(Calendar.DAY_OF_MONTH)
		
            // Let's build our day of year indexes.
            // We don't need exact day of year, just a scale
            def curDOY = curDay + (curMonth * 31)
            def startDOY = startMonth * 31 + startDay
            def endDOY = endMonth * 31 + endDay
		
            /*	Case A:
            S         E
             *------|======|--------*
            Case B:
            E             S
             *===|-------------|====* 	*/

            if (startDOY < endDOY) {
                // Case A
                if (curDOY < startDOY || endDOY < curDOY) {
                    seasonalActive = false
                }
            } else if (startDOY > endDOY) {
                // Case B
                if (startDOY < curDOY  && curDOY < endDOY) {
                    seasonalActive = false
                }
            } else if (startDOY != curDOY) {
                // Case stupid, startDOY == endDOY
                seasonalActive = false
            }
        }
        return seasonalActive
    }

	/** This is a helper function to mark this contact point
	as inactive as of today. */
    void terminate() {

        def today = new Date()
		today.clearTime()
        endDate = today
    }

	/** This static belongsTo map ties this class
	to a edu.umn.ncs.Person object via the 'person'
	attribute. */
    static belongsTo = [ person : Person ]
	
	/** This static hasMany map links this contact point
	to a collection of edu.umn.ncs.CommentPersonContact 
	objects via the 'comments' attribute. */
    static hasMany = [ comments: CommentPersonContact ]
	
	/**
	This contains any constraints for this domain class.
	Non-default constraints for this class are <ul>
		<li>proxyContactID is nullable (optional)</li>
		<li>endDate is nullable (optional)</li>
		<li>lastUpdated is nullable (optional)</li>
		<li>userUpaded is nullable (optional)</li>
		<li>infoDate is nullable (optional)</li>
		<li>infoSource is nullable (optional)</li>
		<li>startMonth is restricted to the range 0-12</li>
		<li>startDay is restricted to the range 1-31</li>
		<li>endMonth is restricted to the range 0-12</li>
		<li>endDay is restricted to the range 1-31</li>
	</ul>
	*/
    static constraints = {
        proxyContactID(nullable:true)
        startDate()
        endDate(nullable:true)
        okToUse()
        preferredOrder()

        // BEGIN PROVENANCE FIELDS
        dateCreated()
        userCreated()
        appCreated()
        lastUpdated(nullable:true)
        userUpdated(nullable:true)
        // END PROVENANCE FIELDS

        infoDate(nullable:true)
        infoSource(nullable:true)

        startMonth(range:0..12)
        startDay(range:1..31)
        endMonth(range:0..12)
        endDay(range:1..31)
    }
    
	/** The following transients are documents
	in their respective get*() functions.
	*/
    static transients = ['active', 'seasonal', 
		'seasonalActive', 'contactClass', 
		'contactCategory', 'contactType']
    
	/** This sets the default sort order for this class
	to be 'preferredOrder'.
	*/
    static mapping = {
    	sort 'preferredOrder'
    }
}

