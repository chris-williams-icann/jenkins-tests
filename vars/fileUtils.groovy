package org.christest

import java.io.File

def getFilesMatchingPattern(globPattern) {
    def files = findFiles(glob: globPattern)
    return files
}

def fileObjsToPaths(fileObjs) {
    def result = []
    def wrap = findFiles(glob: '*')
    wrap.each { it, i ->
      def full_path = it.getPath()
      result.add(full_path)
    }
    return result
}

def getLastModified(fileObjs) {
    if (!fileObjs instanceof File[]) {
        throw new IllegalArgumentException("The paths fileObjs must be an array of File objects")
    }
    // def lastMod = new File('')
    for (file in fileObjs) {
        println "file type: ${file.class}"
        println "file: ${file} lastModified${file.lastModified}"
        /*
        if (lastMod == null || file.lastModified > lastMod.lastModified) {
            println "file type: ${file.class} lastMod type: ${lastMod.class}"
            // lastMod = file
            println "file: ${file} is more recent ${file.lastModified}"
        } else {
            println "file: ${file} is NOT more recent"
        }
        */
    }
    println "most recent file is : ${lastMod}"
    return lastMod
}

def pathsToFileObjects(paths) {
    if (!paths instanceof String[]) {
        throw new IllegalArgumentException("The paths parameter must be an array of strings")
    }
    def fileObjects = [] as List<File>
    for (path in paths) {
        fileObjects << new File(path)
    }
    return fileObjects
}

def sortFilesByLastModified(paths) {
    if (!paths instanceof String[]) {
        throw new IllegalArgumentException("The paths parameter must be an array of strings")
    }
    // Convert file paths to File objects
    def fileObjects = paths.collect { new File(it) }

    // Sort files by last modified date
    def sortedFiles = fileObjects.sort { a, b ->
        long aLastModified = a.lastModified
        long bLastModified = b.lastModified

        // Compare last modified dates in descending order
        bLastModified <=> aLastModified
    }

    // Convert File objects back to file paths
    def sortedFilePaths = sortedFiles.collect { it.toString() }

    return sortedFilePaths
}

def sortFilesObjsByLastModified(fileObjs) {
    if (!fileObjs instanceof File[]) {
        throw new IllegalArgumentException("The paths fileObjs must be an array of File objects")
    }
    def sortedFiles = fileObjs.sort { a, b ->
        def aModified = a.lastModified
        def bModified = b.lastModified
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
