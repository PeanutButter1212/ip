import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import peanut.Deadline;
import peanut.Event;
import peanut.Storage;
import peanut.Task;
import peanut.TaskList;
import peanut.ToDo;


public class StorageTest {

    @TempDir
    Path tempDir; //temp directory to use for testing so it dosent mess up actual

    @Test
    public void saveAndLoad_success() {
        File testFile = tempDir.resolve("test.txt").toFile();
        Storage storage = new Storage(testFile.getAbsolutePath());

        TaskList list = new TaskList(List.of(
                new ToDo("eat"),
                new Deadline("sleep", "2025-10-15"),
                new Event("sleep more", "2025-11-06", "2025-11-05")
        ));

        // Act: save tasks, then load them back
        storage.save(list);
        List<Task> loaded = storage.load();

        // Assert: check sizes and contents
        assertEquals(3, loaded.size());
        assertTrue(loaded.get(0) instanceof ToDo);
        assertTrue(loaded.get(1) instanceof Deadline);
        assertTrue(loaded.get(2) instanceof Event);

        assertEquals("eat", loaded.get(0).getDescription());
        assertEquals("sleep", loaded.get(1).getDescription());
        assertEquals("sleep more", loaded.get(2).getDescription());

    }
}
