package edu.umn.ncs

/**
This domain class defines the different types of relationships
that are associated between People instances.
*/
class RelationshipType implements Serializable {

	/**
	The textual representation of the RelationshipType instance:
	for example "brother", "sister", "mother", etc...
	The maximum length for this string is 64 characters.
	*/
    String name

	/** This defines the default inverse relationship
	if available.  For example, the defaul inverse relation
	for daughter would be parent, and for mother would be
	child.  This is helpful for inserting verbage in letters
	*/
    RelationshipType inverse

	/** This flag sets whether this is a bi-directional
	relationship, such as sister, brother, mother, son... */
    boolean bidirectional = false

	/**
	The toString() method for this domain class.
	It simply returns the name string.
	*/
    String toString() { name }

	/**
	The field constraints applied to this domain
	class.  Non default constraints are:
	<dl>
		<dt>name</dt>
		<dd>maximum length is 64 characters</dd>
		<dt>inverse</dt>
		<dd>this field is optional (nullable)</dd>
	</dl>
	*/
    static constraints = {
    	name(maxSize:64)
        inverse(nullable:true)
    	bidirectional()
    }

	/**
		This mapping defines the default sort order of this domain class to be by name
	*/
    static mapping = {
		sort 'name'
    }
}
