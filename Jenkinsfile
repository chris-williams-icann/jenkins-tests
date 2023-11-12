@Library('chris-test-shared-lib') _
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Getting files from repo'
                checkout scm
                sh 'ls -lt'
                script {
                    log.info('Howdy, partner')
                    def file_select = '\\*.md'
                    def text_files = fileUtils.getZipFiles(file_select)
                    println "${text_files}"
                }
                // Add your build steps here
            }
        }
    }
}
