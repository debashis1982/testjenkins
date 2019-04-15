// MyProject-Build.groovy
mport jenkins.model.Jenkins
Jenkins j = Jenkins.instance

if(!j.isQuietingDown()) {
    def job_dsl_security = j.getExtensionList('javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration')[0]
    if(job_dsl_security.useScriptSecurity) {
        job_dsl_security.useScriptSecurity = false
        println 'Job DSL script security has changed.  It is now disabled.'
        job_dsl_security.save()
        j.save()
    }
    else {
        println 'Nothing changed.  Job DSL script security already disabled.'
    }
}
else {
    println 'Shutdown mode enabled.  Configure Job DSL script security SKIPPED.'
}

def gitUrl = "https://github.com/debashis1982/MySpringBoot"

job("MyProject-Build") {
    description "Builds MyProject from master branch."
    parameters {
        stringParam('COMMIT', 'HEAD', 'Commit to build')
    }
    scm {
        git {
            remote {
                url gitUrl.
                branch "origin/master"
            }
            extensions {
                wipeOutWorkspace()
                localBranch master
            }
        }
    }
    steps {
        shell "Look: I'm building master!"
    }
}
