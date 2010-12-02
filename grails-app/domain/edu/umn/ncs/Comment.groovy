package edu.umn.ncs

class Comment implements Serializable {

	String text
	Date dateCreated = new Date()
	String userCreated = 'unknown'
	String appCreated

	String toString() { text }

    static constraints = {
    	text(maxSize:4000)
		dateCreated()
		userCreated(maxSize:16)
		appCreated(maxSize:50)
    }
    
}
