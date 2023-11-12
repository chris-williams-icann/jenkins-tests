package org.christest

import java.io.File

def getFilesMatchingPattern(globPattern) {
    def files = findFiles(glob: globPattern)
    return files
}

def sortFilesByLastModified(files) {
    files.sort { a, b ->
        def aModified = a.lastModified()
        def bModified = b.lastModified()
        return bModified <=> aModified
    }
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
