package peanut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * The Storage class handles file saving and loading
 * It is responsible for saving TaskList into a txt file and
 * loading the txt file when run
 */
public class Storage {
    private String filePath;
    /**
     * Creates storage object with given filepath
     *
     * @param filePath path to the file for saving/loading
     */
    public Storage (String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads task from file into a list of tasks
     * If file/folder does not exist, empty list is returned
     *
     * @return A list of task loaded from the file/empty list
     */
    public List<Task> load() {
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        List<Task> tasks = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task t = createTaskHelper(line);
                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }

        return tasks;
    }


    /**
     * Saves task from current list into file
     * Overrides existing file with new contents
     *
     * @param taskList List of tasks that needs to be saved into file
     */
    public void save(TaskList taskList) {
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try ( FileWriter fw = new FileWriter(this.filePath)) {

            for (Task t : taskList.getTasks()) {
                fw.write(t.toFileFormat());
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    private Task createTaskHelper(String taskDetails) {

        String[] parts = taskDetails.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String desc = parts[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new ToDo(desc);
                break;
            case "D":
                task = new Deadline(desc, parts[3]);
                break;
            case "E":
                task = new Event(desc, parts[4], parts[3]);
                break;
        }

        if (task != null && isDone) {
            task.mark();
        }
        return task;
    }

}


