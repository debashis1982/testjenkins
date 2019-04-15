// MyProject-Build.groovy
def gitUrl = "https://github.com/debashis1982/MySpringBoot"

job('Build-MySpringBoot') {
    scm {
        git(gitUrl)
    }
    triggers {
        scm('*/15 * * * *')
    }
    steps {
        maven('-e clean test')
    }
}
