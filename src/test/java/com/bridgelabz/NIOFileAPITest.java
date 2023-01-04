package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

/**
 * @author Govardhan Reddy
 */

public class NIOFileAPITest {
    static String home = System.getProperty("user.home");
    static String playWithNIO = "TemplePlayGround";

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
        //check file exits
        Path homePath = Paths.get(home);
        Assert.assertTrue(Files.exists(homePath));
        //Delete file check file not exit
        Path playPath = Paths.get(home + "palyWithNIO");
        if (Files.exists(playPath)) {
            FileUtils.deleteFiles(playPath.toFile());
        }
        Assert.assertTrue(Files.notExists(playPath));
        //Create a directory
        IntStream.range(1, 10).forEach(x -> {
            Path tempFile = Paths.get(playPath + "/temp" + x);
            Assert.assertTrue(Files.exists(tempFile));
            try {
                Files.createFile(tempFile);
            } catch (IOException e) {
            }
            Assert.assertTrue(Files.exists(tempFile));
        });
        //List files , Directories se well as files with extensions
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("temp")).forEach(System.out::println);
    }

    @Test
    public void givenADirectoryWhenWatchListAllTheActivities() throws IOException {
        Path directory = Paths.get(home + "/" + playWithNIO);
        Files.list(directory).filter(Files::isRegularFile).forEach(System.out::println);
        new Java8WatchServiceExample(directory).processEvents();
    }

}
