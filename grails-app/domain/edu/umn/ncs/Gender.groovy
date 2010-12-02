package edu.umn.ncs

// This should pull the name description from the
// i18n file for proper internationalization
// support.
class Gender implements Serializable {

	String name
	
	String toString() { name }
	
    static constraints = {
    	name(maxSize: 7)
    }
}
