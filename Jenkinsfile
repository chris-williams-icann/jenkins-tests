@Library('chris-test-shared-lib') _

import java.io.File

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {

                echo 'Getting files from repo'
                checkout scm
                script {
                    def updatedPackages = fileUtils.gitDiffAndFilterSortFiles('packages/*/*.zip')
                    echo "Updated Packages: ${updatedPackages}"     
                }
                sh 'touch -a -m -t 202311091300 packages/P001/abc.zip'
                sh 'touch -a -m -t 202311081300  packages/P002/jkl.zip'
                sh 'stat packages/*/*.zip'
                script {
                    log.info('Howdy, partner')
                    def file_select = 'packages/*/*.zip'
                    def zip_files = fileUtils.getFilesMatchingPattern(file_select)
                    println "zip_files: ${zip_files}"

                    def sorted_files = fileUtils.sortFilesByLastModified(zip_files)
                    println "sorted_files: ${sorted_files}"
                }
            }
        }
    }
}
