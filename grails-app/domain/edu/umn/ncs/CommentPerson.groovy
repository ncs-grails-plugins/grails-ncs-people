package edu.umn.ncs

/**
This class extends edu.umn.edu.Comment by tying it to a particular edu.umn.ncs.Person instance.
*/
class CommentPerson extends Comment implements Serializable {
	/**
	The static belongsTo exposes the 'person' attribute for this class that
	the comment belongs to.  This is a required attribute, as it is part of the 'belongsTo' map
	*/
	static belongsTo = [ person : Person ]
}
