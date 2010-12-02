package edu.umn.ncs

class CommentPerson extends Comment implements Serializable {
	static belongsTo = [ person : Person ]
}
