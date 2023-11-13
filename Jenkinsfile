@Library('chris-test-shared-lib') _

import java.io.File

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Getting files from repo'
                checkout scm
                sh 'touch -m -240000 packages/P001/abc.zip'
                sh 'touch -m -240000 packages/P002/jkl.zip'
                sh 'stat packages/*/*.zip'
                script {
                    log.info('Howdy, partner')
                    def file_select = 'packages/*/*.zip'
                    def zip_files = fileUtils.getFilesMatchingPattern(file_select)
                    println "zip_files: ${zip_files}"
                    def last_mod = fileUtils.getLastModified(zip_files)
                    println "last_mod: ${last_mod}"
                    // Sort files by last modified date
                    zip_files.sort { a, b ->
                        def aModified = a.lastModified
                        def bModified = b.lastModified
                        println "a: ${a} aModified=${aModified}"
                        println "b: ${b} bModified=${bModified}"
                        return bModified <=> aModified
                    }
                    println "After zip_files: ${zip_files}"
                }
            }
        }
    }
}
