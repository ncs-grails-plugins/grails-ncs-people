package edu.umn.ncs

/**
This domain class represents the various ethincities available,
often needed for demograpic informaiton when gathering baseline
data for a study.
Sometimes ethnicity is referred to as race, but technically
there is only one human race, so we're using ethinicity here.
*/
class Ethnicity implements Serializable {

	/**
	This is the plain english name for this ethincity grouping.
	For example: 'Smurf', or 'Decepticon'
	*/
	String name
	
	/**
	This is used to hold whatever abbreviation you wish to assign to a particular
	ethinicity.
	For example, 'Caucasian' could be abbreviated by 'WH'.
	*/
	String abbreviation

	/**
	This is the method that is the default converter of this class to a string.
	This toString() method just returns the contents of the 'name' attribute
	*/
	String toString() { name }
		
	/**
	This contains any constraints for the domain class.
	Non-default constraints for this class are <ul>
		<li>the abbreviation is limited to 2 characters</li>
		<li>the name is limited to a maximum of 30 characters</li>
	</ul>
	*/
    static constraints = {
	    abbreviation(maxSize: 2)
    	name(maxSize: 30)
    }
}
