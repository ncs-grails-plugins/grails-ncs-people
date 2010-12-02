package edu.umn.ncs

class RelationshipType implements Serializable {

    String name
    RelationshipType inverse
    boolean bidirectional = false

    String toString() { name }

    static constraints = {
    	name(maxSize:64)
        inverse(nullable:true)
    	bidirectional()
    }
}
