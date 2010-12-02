package edu.umn.ncs

class PhoneType implements Serializable {

    String name
    boolean allowAddressLink
	
    String toString() {
        name
    }

    static constraints = {
    	name(maxSize:20)
    	allowAddressLink()
    }
}
