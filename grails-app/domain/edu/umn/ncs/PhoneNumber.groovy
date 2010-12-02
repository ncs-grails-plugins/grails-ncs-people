package edu.umn.ncs

class PhoneNumber implements Serializable {

    int			countryCode = 1
    String		phoneNumber
    String		extension

    // BEGIN PROVENANCE FIELDS
    Date		dateCreated = new Date()
    String		userCreated = 'unknown'
    String		appCreated
    Date		lastUpdated
    String		userUpdated
    // END PROVENANCE FIELDS

    static transients = [ 'prettyPhone' ]

    String getPrettyPhone() {
        def phone = phoneNumber

            if ( phone.size() == 10) {
                phone =  phone.substring(0,3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6)
            } else if(phone.size() == 9) {
                phone =  phone.substring(0,3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6)
            } else if(phone.size() == 8) {
                phone =  phone.substring(0,1) + "-" + phone.substring(1, 4) + "-" + phone.substring(4)
            } else if(phone.size() == 7) {
                phone =  phone.substring(0, 3) + "-" + phone.substring(3)
            } else if(phone.size() == 6) {
                phone =  phone.substring(0,3) + "-" + phone.substring(3)
            } else if(phone.size() == 5) {
                phone =  phone.substring(0,2) + "-" + phone.substring(2)
            }

        return phone
    }

    static constraints = {
        countryCode()
        phoneNumber(matches:'[0-9)(.-]+', maxSize:24)
        extension(nullable:true, maxSize:16)
        prettyPhone(nullable:true, maxSize:32)
        // BEGIN PROVENANCE FIELDS
        dateCreated()
        userCreated()
        appCreated()
        lastUpdated(nullable:true)
        userUpdated(nullable:true)
        // END PROVENANCE FIELDS
    }
}
