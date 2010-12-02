package edu.umn.ncs

class ContactRoleType implements Serializable {

	String name
	
	String toString() {
		name
	}
	
    static constraints = {
    	name(maxSize: 24)
    }
}
