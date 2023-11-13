@Library('chris-test-shared-lib') _

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Getting files from repo'
                checkout scm
                sh 'touch -A -240000 packages/P001/abc.zip'
                sh 'touch -A -240000 packages/P002/jkl.zip'
                sh 'stat packages/*/*.zip'
                script {
                    log.info('Howdy, partner')
                    def file_select = 'packages/*/*.zip'
                    def zip_files = fileUtils.getFilesMatchingPattern(file_select)
                    println "zip_files: ${zip_files}"
                    def sorted_files = fileUtils.sortFilesByLastModified(zip_files)
                    println "sorted_files: ${sorted_files}"
                    // Sort files by last modified date
                    def sortedFiles = zip_files.sort { it.getLastModified() }
                    println "sortedFiles: ${sortedFiles}"
                    println "After zip_files: ${zip_files}"
                }
            }
        }
    }
}
