package edu.umn.ncs

class EnrollmentType implements Serializable {

	String name

	String toString() { name }

    static constraints = {
    	name(maxSize:16)
	}
}
