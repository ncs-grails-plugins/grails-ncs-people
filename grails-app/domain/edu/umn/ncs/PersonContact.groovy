package edu.umn.ncs

// SUPERCLASS
// This class is inherited by many other classes.
// DO NOT USE IT DIRECTLY!

class PersonContact implements Serializable {

    Person proxyContactID

    Date startDate = new Date()
    Date endDate

    int	startMonth = 0
    int	startDay = 1
    int	endMonth = 0
    int	endDay = 31

    boolean okToUse = true
    int preferredOrder = 0

    // BEGIN PROVENANCE FIELDS
    Date		dateCreated = new Date()
    String		userCreated = 'unknown'
    String		appCreated
    Date		lastUpdated
    String		userUpdated
    // END PROVENANCE FIELDS

    Date			infoDate
    Source			infoSource

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

    String getContactType() {
        def className = this.class.toString()
        className = className.replace('class enhs.Person', '')
        return className
    }

    // Check to see if this address is active/valid
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

    // See if this address is a "snow bird" address
    boolean isSeasonal() {
        if (startMonth && endMonth) {
            return true
        } else {
            return false
        }
    }

    // See if this seasonal address is in it's window
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

    // Disable this address
    void terminate() {

        def cal = new GregorianCalendar()
        cal.set(Calendar.HOUR, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        def today = new Date(cal.timeInMillis)
        endDate = today
    }

    static belongsTo = [ person : Person ]
	
    static hasMany = [ comments: CommentPersonContact ]
	
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
    
    static transients = ['active', 'seasonal', 
		'seasonalActive', 'contactClass', 
		'contactCategory', 'contactType']
    
    static mapping = {
    	sort 'preferredOrder'
    }
}

