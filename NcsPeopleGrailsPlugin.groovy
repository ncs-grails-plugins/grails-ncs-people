class NcsPeopleGrailsPlugin {
    // the plugin version
    def version = "0.9"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
			"grails-app/conf/Config.groovy",
			"lib/"
    ]

    def author = "Aaron J. Zirbes"
    def authorEmail = "ajz@umn.edu"
    def title = "Contact Point Structure"
    def description = '''\\
Case Management - Contact Point Structure
'''

    // URL to the plugin's documentation
    def documentation = "https://secure.ncs.umn.edu/api/api/ncs-people/"
}
