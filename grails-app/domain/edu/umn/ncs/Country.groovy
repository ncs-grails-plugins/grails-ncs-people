package edu.umn.ncs

class Country implements Serializable {

	String abbreviation
	String name
	
	String toString() {
		name
	}
	
    static constraints = {
		abbreviation(maxSize: 2)
		name(maxSize: 45)
    }
}
