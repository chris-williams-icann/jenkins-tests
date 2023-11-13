urn package org.christest

import java.io.File

def getFilesMatchingPattern(globPattern) {
    def files = findFiles(glob: globPattern)
    return files
}

def sortFilesByLastModified(files) {
    // Convert file paths to File objects
    def fileObjects = files.collect { new File(it) }

    // Sort files by last modified date
    def sortedFiles = fileObjects.sort { a, b ->
        long aLastModified = a.lastModified()
        long bLastModified = b.lastModified()

        // Compare last modified dates in descending order
        bLastModified <=> aLastModified
    }

    // Convert File objects back to file paths
    def sortedFilePaths = sortedFiles.collect { it.toString() }

    return sortedFilePaths
}

def sortFilesObjsByLastModified(files) {
    def sortedFiles = files.sort { a, b ->
        def aModified = a.getLastModified()
        def bModified = b.getLastModified()
        println "a: ${a} aModified=${aModified}"
        println "b: ${b} bModified=${bModified}"
        return bModified <=> aModified
    }
    return sortedFiles
}

def getZipFiles(antExpression) {
    def files = [] as List<File>
    def directory = new File(workspace)
    directory.eachFile { file ->
        if (file.name.matches(antExpression)) {
            files << file
        }
    }
    return files
}

def sortZipFilesByLastModified(files) {
    files.sort { a, b ->
        def aModified = a.lastModified()
        def bModified = b.lastModified()
        return bModified <=> aModified
    }
}
