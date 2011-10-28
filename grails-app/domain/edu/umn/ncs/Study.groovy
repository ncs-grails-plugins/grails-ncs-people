package edu.umn.ncs

/**
This class represents a study.
*/
class Study implements Serializable {

	/** This is the "short" name for the study,
	often this is an abbreviation */
	String name
	/** This is the full name of the study.
	It should not contain any abbreviations.
	*/
	String fullName
	/** This flag tells whether or not the
	study is in an active state. */
	boolean active
	/** This string can hold the study sponsor informaiton if available.
	Commonly this is the "National Cancer Institute" */
	String sponsor
	/** This string holds the coordinating site/center/group if available.
	For example: "National Institute of Child Health and Development" */
	String coordinator
	/** This string holds a list of collaborators on the study.
	For example: "National Option Research Group" */
	String collaborator
	/** This is a short phrase to describe the study.
	This is usually the mission statement of the study.
	For example: "to improve the health and well-being 
	of children and contribute to understanding the 
	role various factors have on health and disease"
	*/
	String purpose
	/** This flags whether or not involvment in this study excludes the
	subject from involvement in any other study, or sub-study.
	*/
	boolean exclusionary // Participation excludes from other studies
	/** If this is a sub study, this should contain a reference to the
	main study that this is a sub study of. */
	Study subStudyOf

	/**
	This is the method that is the default converter of this class to a string.
	This toString() method just returns the contents of the 'name' attribute
	*/
	String toString() {
		name
	}


	/**
	This contains any constraints for the domain class.
	Non-default constraints for this class are <ul>
		<li>the subStudyOf attribute is optional (nullable)</li>
	</ul>
	*/
    static constraints = {
		name()
		fullName()
		active()
		sponsor()
		coordinator()
		collaborator()
		purpose()
		exclusionary()
		subStudyOf(nullable: true)
    }
}
