grails.config.locations = [ "file:/etc/grails/ncs-case-management-config.groovy" ]

// grails.doc.authors - The authors of the documentation
grails.doc.authors = "Aaron J. Zirbes"
// grails.doc.license - The license of the software
grails.doc.license = "GNU General Public License, version 3"
// grails.doc.copyright - The copyright message to display
grails.doc.copyright = "Copyright (c) Regeants of the University of Minnesota, 2011.  Written the National Children's Study"
// grails.doc.footer - The footer to use
grails.doc.footer = "https://www.healthstudies.umn.edu/ | http://nationalchildrensstudy.gov/"

grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"

// Added by the Joda-Time plugin:
grails.gorm.default.mapping = {
	"user-type" type: org.joda.time.contrib.hibernate.PersistentDateTime, class: org.joda.time.DateTime
	"user-type" type: org.joda.time.contrib.hibernate.PersistentDuration, class: org.joda.time.Duration
	"user-type" type: org.joda.time.contrib.hibernate.PersistentInstant, class: org.joda.time.Instant
	"user-type" type: org.joda.time.contrib.hibernate.PersistentInterval, class: org.joda.time.Interval
	"user-type" type: org.joda.time.contrib.hibernate.PersistentLocalDate, class: org.joda.time.LocalDate
	"user-type" type: org.joda.time.contrib.hibernate.PersistentLocalTimeAsString, class: org.joda.time.LocalTime
	"user-type" type: org.joda.time.contrib.hibernate.PersistentLocalDateTime, class: org.joda.time.LocalDateTime
	"user-type" type: org.joda.time.contrib.hibernate.PersistentPeriod, class: org.joda.time.Period
}
