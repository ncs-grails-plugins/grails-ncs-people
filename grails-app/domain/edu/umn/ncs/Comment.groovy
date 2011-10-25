package edu.umn.ncs

/**
<p>Comment is a base class that provides the basic 
elements of what is needed to track comments, or can be extended to provide
comments specific to a particular domain class.</p>
<p>The Comment domain class ensures that the who and when
of a comment is recorded alon with a comment.</p>
*/
class Comment implements Serializable {

	/**
	This is the meat of the comment and contains the actual comment itself.
	The maximum length of the text field is 4000 characters, so use them wisely.
	*/
	String text

	/**
	This logs the date and time that the comment was recorded.
	*/
	Date dateCreated = new Date()

	/**
	This logs the user who entered the comment into the system
	*/
	String userCreated = 'unknown'
	/**
	This logs the application that was used to enter the comment
	*/
	String appCreated

	/**
	This is the method that is the default converter of this class to a string.
	This toString() method just returns the contents of the 'text' attribute
	*/
	String toString() { text }

	/**
	This contains any constraints for the domain class.
	Non-default constraints for this class are <ul>
		<li>the text attribute can be no longer than 4000 characters</li>
		<li>the userCreated attribute can be no longer than 16 characters</li>
		<li>the appCreated attribute can be no longer than 50 characters</li>
	</ul>
	*/
    static constraints = {
    	text(maxSize:4000)
		dateCreated()
		userCreated(maxSize:16)
		appCreated(maxSize:50)
    }

	/**
		This mapping defines the default sort order of this domain class to be by text
	*/
    static mapping = {
		sort 'text'
    }
}
