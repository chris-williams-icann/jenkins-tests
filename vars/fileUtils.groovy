package org.christest

import java.io.File
import org.jenkinsci.plugins.pipeline.utility.steps.fs.FileWrapper

def gitDiffAndFilterSortFiles(pattern) {
    println "Getting the list of changed files"
    def gitDiffCommand = 'git diff --name-only main'
    def changedFiles = sh(script: gitDiffCommand, returnStdout: true).trim().split('\n')

    println "Filter files based on the provided pattern"
    def filteredFiles = changedFiles.findAll { file ->
        file =~ pattern
    }
    println "Filtered files; ${filteredFiles}"

    println "Sort the filtered files by file name in descending order"
    def sortedFiles = filteredFiles.sort { a, b ->
        b <=> a
    }

    // Step 4: Print the sorted file names (for demonstration purposes)
    sortedFiles.each { file ->
        println "Sorted File: ${file}"
    }

    // Return the sorted file list (optional)
    return sortedFiles
}

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
    if (fileObjs.size() < 1) {
        return null
    }
    def lastMod = fileObjs[0]
    for (file in fileObjs) {
        if (file.lastModified > lastMod.lastModified) {
            lastMod = file
        }
    }
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
