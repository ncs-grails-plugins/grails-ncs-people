package edu.umn.ncs

class Ethnicity implements Serializable {

	String name
	String abbreviation

	String toString() { name }
		
    static constraints = {
	    abbreviation(maxSize: 2)
    	name(maxSize: 30)
    }
}
