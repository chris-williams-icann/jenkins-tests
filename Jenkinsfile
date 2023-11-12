@Library('chris-test-shared-lib') _
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Getting files from repo'
                checkout scm
                sh 'echo "Hello world"'
                script {
                    log.info('Howdy, partner')
                    def file_select = '**/*'
                    def text_files = fileUtils.getZipFiles(file_select)
                    println "${text_files}"
                }
                // Add your build steps here
            }
        }
    }
}
