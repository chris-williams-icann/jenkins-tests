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
                    def file_select = 'packages/*/*.zip'
                    def zip_files = fileUtils.getFilesMatchingPattern(file_select)
                    println "zip_files: ${zip_files}"
                    def sorted_files = fileUtils.sortFilesByLastModified(zip_files)
                    println "sorted_files: ${sorted_files}"
                }
                // Add your build steps here
            }
        }
    }
}
