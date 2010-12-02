package edu.umn.ncs

class Study implements Serializable {

	String name
	String fullName
	boolean active
	String sponsor
	String coordinator
	String collaborator
	String purpose
	boolean exclusionary // Participation excludes from other studies

	String toString() {
		name
	}
    static constraints = {
		name()
		fullName()
		active()
		sponsor()
		coordinator()
		collaborator()
		purpose()
		exclusionary()
    }
}
