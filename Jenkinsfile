@Library('chris-test-shared-lib') _

import java.io.File

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
                    def last_mod = fileUtils.getLastModified(zip_files)
                    println "last_mod: ${last_mod}"
                    def new_sort = fileUtils.sortFilesObjsByLastModified(zip_files)
                    println "new_sort: ${new_sort}"
                    // Sort files by last modified date
                    zip_files.sort { a, b ->
                        def aModified = a.getLastModified()
                        def bModified = b.getLastModified()
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
