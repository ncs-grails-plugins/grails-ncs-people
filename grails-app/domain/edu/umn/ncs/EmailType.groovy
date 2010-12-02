package edu.umn.ncs

class EmailType implements Serializable {

	String name

	String toString() {
		name
	}

    static constraints = {
    	name(maxSize:16)
    }    

    static mapping = {
	sort 'name'
    }
}
