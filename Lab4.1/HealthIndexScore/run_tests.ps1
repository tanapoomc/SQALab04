# Compiling classes
Write-Host "Compiling Java files..." -ForegroundColor Cyan
javac -d bin -cp "junit-platform-console-standalone.jar" src/com/health/fitness/*.java

# Checking compile status
if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed." -ForegroundColor Red
    Exit $LASTEXITCODE
}

Write-Host "Compilation successful. Running JUnit tests..." -ForegroundColor Cyan

# Running JUnit Platform Standalone Console
java "-Dfile.encoding=UTF-8" -jar junit-platform-console-standalone.jar --class-path bin --select-package com.health.fitness

