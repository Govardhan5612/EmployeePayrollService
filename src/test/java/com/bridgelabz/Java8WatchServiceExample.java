package com.bridgelabz;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Govardhan Reddy
 */
public class Java8WatchServiceExample {
    WatchService watcher;
    Map<WatchKey, Path> directoryWatcher;

    //creates a watch service and registers the given directory
    Java8WatchServiceExample(Path directory) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.directoryWatcher = new HashMap<>();
        scanAndRegisterDirectories(directory);
    }

    //Register the given directory with the watch service
    private void registerDirectoryWatches(Path directory) throws IOException {
        WatchKey key = directory.register(watcher);
        directoryWatcher.put(key, directory);
    }

    //  Register the given directory, and all its sub directories, with the watch service
    private void scanAndRegisterDirectories(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) throws IOException {
                registerDirectoryWatches(directory);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    //process all events for keys queued to the watcher
    @SuppressWarnings({"rawtypes", "unchecked"})
    void processEvents() {
        while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException exception) {
                return;
            }
            Path directory = directoryWatcher.get(key);
            if (directory == null) continue;
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = directory.resolve(name);
                System.out.format("%s:%s\n", event.kind().name(), child);//print out event
                //if directory is created then register it and its sub directories
                if (kind != null) {
                    try {
                        if (Files.isDirectory(child)) scanAndRegisterDirectories(child);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            // reset key remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                directoryWatcher.remove(key);
                if (directoryWatcher.isEmpty()) break;
            }
        }
    }
}
