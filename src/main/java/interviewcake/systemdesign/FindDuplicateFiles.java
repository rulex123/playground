package interviewcake.systemdesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You left your computer unlocked and your friend decided to troll you by copying a lot of your
 * files to random spots all over your file system.
 *
 * Even worse, she saved the duplicate files with random, embarrassing names
 * ("this_is_like_a_digital_wedgie.txt" was clever, I'll give her that).
 *
 * Write a method that returns a list of all the duplicate files. We'll check them by hand before
 * actually deleting them, since programmatically deleting files is really scary. To help us
 * confirm that two files are actually duplicates, return a list of <code>FilePaths</code>
 * objects with variables for the original and duplicate paths.
 *
 * Example of result:
 *
 * [(duplicate: /tmp/parker_is_dumb.mpg, original: /home/parker/secret_puppy_dance.mpg),
 * (duplicate: /home/trololol.mov, original: /etc/apache2/httpd.conf)]
 *
 * You can assume each file was only duplicated once.
 */
public class FindDuplicateFiles {

  public static List<FilePaths> findDuplicateFiles(Path startingDirectory) {
    Map<String, FileInfo> filesSeenAlready = new HashMap<>();
    Deque<Path> stack = new ArrayDeque<>();
    stack.push(startingDirectory);

    List<FilePaths> duplicates = new ArrayList<>();

    while (!stack.isEmpty()) {

      Path currentPath = stack.pop();
      File currentFile = new File(currentPath.toString());

      // if it's a directory,
      // put the contents in our stack
      if (currentFile.isDirectory()) {
        for (File file : currentFile.listFiles()) {
          stack.push(file.toPath());
        }

        // if it's a file
      } else {

        // get its hash
        String fileHash;
        try {
          fileHash = sampleHashFile(currentPath);
        } catch (IOException | NoSuchAlgorithmException e) {
          // show error and skip this file
          e.printStackTrace();
          continue;
        }

        // get its last edited time
        long currentLastEditedTime = currentFile.lastModified();

        // if we've seen it before
        if (filesSeenAlready.containsKey(fileHash)) {

          FileInfo fileInfo = filesSeenAlready.get(fileHash);
          long existingLastEditedTime = fileInfo.timeLastEdited;
          Path existingPath = fileInfo.path;

          if (currentLastEditedTime > existingLastEditedTime) {

            // current file is the dupe!
            duplicates.add(new FilePaths(currentPath, existingPath));

          } else {

            // old file is the dupe!
            duplicates.add(new FilePaths(existingPath, currentPath));

            // but also update filesSeenAlready to have the new file's info
            filesSeenAlready.put(fileHash, new FileInfo(currentLastEditedTime, currentPath));
          }

          // if it's a new file, throw it in filesSeenAlready
          // and record its path and last edited time,
          // so we can tell later if it's a dupe
        } else {
          filesSeenAlready.put(fileHash, new FileInfo(currentLastEditedTime, currentPath));
        }
      }
    }
    return duplicates;
  }

  private static final int SAMPLE_SIZE = 4000; // this value will vary based on file system

  private static String sampleHashFile(Path path) throws IOException, NoSuchAlgorithmException {

    final long totalBytes = new File(path.toString()).length();

    try (InputStream inputStream = new FileInputStream(path.toString())) {
      MessageDigest digest = MessageDigest.getInstance("SHA-512");
      DigestInputStream digestInputStream = new DigestInputStream(inputStream, digest);

      // if the file is too short to take 3 samples, hash the entire file
      if (totalBytes < SAMPLE_SIZE * 3) {
        byte[] bytes = new byte[(int) totalBytes];
        digestInputStream.read(bytes);
      } else {
        byte[] bytes = new byte[SAMPLE_SIZE * 3];
        long numBytesBetweenSamples = (totalBytes - SAMPLE_SIZE * 3) / 2;

        // read first, middle and last bytes
        for (int n = 0; n < 3; n++) {
          digestInputStream.read(bytes, n * SAMPLE_SIZE, SAMPLE_SIZE);
          digestInputStream.skip(numBytesBetweenSamples);
        }
      }
      return new BigInteger(1, digest.digest()).toString(16);
    }
  }

  private static class FilePaths {

    private Path duplicatePath;
    private Path originalPath;

    public FilePaths(Path duplicatePath, Path originalPath) {
      this.duplicatePath = duplicatePath;
      this.originalPath = originalPath;
    }

    public Path getDuplicatePath() {
      return duplicatePath;
    }

    public Path getOriginalPath() {
      return originalPath;
    }

    @Override
    public String toString() {
      return String.format("(duplicate: %s, original: %s)", duplicatePath, originalPath);
    }
  }

  private static class FileInfo {

    long timeLastEdited;
    Path path;

    FileInfo(long timeLastEdited, Path path) {
      this.timeLastEdited = timeLastEdited;
      this.path = path;
    }
  }
}

// Solution
// We walk through our whole file system iteratively. As we go, we take a "fingerprint" of each
// file in constant time by hashing the first few, middle few, and last few bytes.
// We store each file's fingerprint in a hash map as we go.
//
// If a given file's fingerprint is already in our hash map, we assume we have a duplicate. In
// that case, we assume the file edited most recently is the one created by our friend.
//
// We've made a few assumptions here:
//
// Two different files won't have the same fingerprints. It's not impossible that two files with
// different contents will have the same beginning, middle, and end bytes so they'll have the same
// fingerprints. Or they may even have different sample bytes but still hash to the same value
// (this is called a "hash collision"). To mitigate this, we could do a last-minute check whenever
// we find two "matching" files where we actually scan the full file contents to see if they're the same.
//
// The most recently edited file is the duplicate. This seems reasonable, but it might be
// wrong - for example, there might be files which have been edited by daemons (programs that run
// in the background) after our friend finished duplicating them.
//
// Two files with the same contents are the same file. This seems trivially true, but it could
// cause some problems. For example, we might have empty files in multiple places in our file
// system that aren't duplicates of each-other.
//
// Given these potential issues, we definitely want a human to confirm before we delete any files.
// Still, it's much better than combing through our whole file system by hand!


// Some ideas for further improvements:
//
// If a file wasn't last edited around the time your friend got a hold of your computer, you know
// it probably wasn't created by your friend. Similarly, if a file wasn't accessed (sometimes your
// filesystem stores the last accessed time for a file as well) around that time, you know it
// wasn't copied by your friend. You can use these facts to skip some files.
//
// Make the file size the fingerprint - it should be available cheaply as metadata on the file (so
// you don't need to walk through the whole file to see how long it is). You'll get lots of false
// positives, but that's fine if you treat this as a "preprocessing" step. Maybe you then take
// hash-based fingerprints only on the files which which have matching sizes. Then you fully
// compare file contents if they have the same hash.
//
// Some file systems also keep track of when a file was created. If your filesystem supports
// this, you could use this as a potentially-stronger heuristic for telling which of two copies
// of a file is the dupe.
//
// When you do compare full file contents to ensure two files are the same, no need to read the
// entire files into memory. Open both files and read them one block at a time. You can
// short-circuit as soon as you find two blocks that don't match, and you only ever need to store
// a couple blocks in memory.


// Complexity
//
// Each "fingerprint" takes O(1) time and space, so our total time and space costs are O(n) where
// n is the number of files on the file system.
//
// If we add the last-minute check to see if two files with the same fingerprints are actually
// the same files (which we probably should), then in the worst case all the files are the same
// and we have to read their full contents to confirm this, giving us a runtime that's order
// of the total size of our files on disk.
