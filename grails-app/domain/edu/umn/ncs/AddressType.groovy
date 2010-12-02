package edu.umn.ncs

class AddressType implements Serializable {

	String name
	
	String toString() { name }

    static constraints = {
    	name(maxSize:16)
    }
}

