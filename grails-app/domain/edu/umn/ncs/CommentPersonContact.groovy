package edu.umn.ncs

class CommentPersonContact extends Comment implements Serializable {
    static belongsTo = [personContact : PersonContact ]
}
