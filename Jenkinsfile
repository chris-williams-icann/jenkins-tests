
import java.io.File

node ('okd-general-worker'){

    def deployUtils = load 'pipeline_scripts/deployUtils.groovy'
    stages {
        stage('Checkout') {
            echo 'Getting files from repo'
            checkout scm
        }
        try {
            stage('Checkout') {
                script {
                    def updatedPackages = fileUtils.gitDiffAndFilterSortFiles('packages/*/*.zip')
                    echo "Updated Packages: ${updatedPackages}"     
                }
            }
        }
        catch(e) {
            currentBuild.result = "FAILURE"
            throw e
        }
    }
}
