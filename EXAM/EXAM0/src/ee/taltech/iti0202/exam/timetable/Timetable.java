package ee.taltech.iti0202.exam.timetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A timetable for managing tasks with constraints on daily task count and duration.
 */
public class Timetable {
    private final List<Task> tasks = new ArrayList<>();
    private int taskCounter = 1;

    /**
     * Represents a task with its properties.
     *
     * @param code Unique identifier for the task
     * @param name Name of the task
     * @param day Day number when the task is scheduled
     * @param duration Duration of the task (1-5 hours)
     * @param priority Whether the task has priority
     * @param done Whether the task is completed
     */
    private record Task(
            String code,
            String name,
            int day,
            int duration,
            boolean priority,
            boolean done
    ) {
        /**
         * Creates a new incomplete task.
         */
        Task(String code, String name, int day, int duration, boolean priority) {
            this(code, name, day, duration, priority, false);
        }

        /**
         * Marks the task as done.
         * @return A new Task instance with done=true
         */
        public Task markDone() {
            return new Task(code, name, day, duration, priority, true);
        }
    }

    /**
     * Adds a new task to the timetable if constraints are satisfied.
     *
     * @param name Name of the task (must be unique per day)
     * @param day Day number (must be ≥1)
     * @param duration Duration in hours (must be 1-5)
     * @param priority Whether the task has priority
     * @return Optional containing task code if added successfully, empty otherwise
     * @throws IllegalArgumentException if day or duration are invalid
     */
    public Optional<String> addTask(String name, int day, int duration, boolean priority) {
        if (day < 1 || duration < 1 || duration > 5) {
            return Optional.empty();
        }

        // Check for duplicate task name on the same day
        for (Task task : tasks) {
            if (task.name().equals(name) && task.day() == day) {
                return Optional.empty();
            }
        }

        // Calculate current day's task count and duration sum
        int dayTaskCount = 0;
        int dayDurationSum = 0;
        for (Task task : tasks) {
            if (!task.done() && task.day() == day) {
                dayTaskCount++;
                dayDurationSum += task.duration();
            }
        }

        // Verify constraints: max 5 tasks and max 5 hours per day
        if (dayTaskCount >= 5 || dayDurationSum + duration > 5) {
            return Optional.empty();
        }

        String code = "T" + taskCounter++;
        Task newTask = new Task(code, name, day, duration, priority);
        tasks.add(newTask);
        return Optional.of(code);
    }

    /**
     * Marks a task as done by its code.
     *
     * @param taskNumber The task code (e.g. "T1")
     * @return true if task was found and marked as done, false otherwise
     */
    public boolean markTaskDone(String taskNumber) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.code().equals(taskNumber)) {
                if (!task.done()) {
                    tasks.set(i, task.markDone());
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * Gets all incomplete tasks for a specific day, sorted by priority.
     *
     * @param day The day number to query (must be ≥1)
     * @return List of task strings in format "code name", with priority tasks first
     */
    public List<String> getTasksForDay(int day) {
        List<Task> priorityTasks = new ArrayList<>();
        List<Task> regularTasks = new ArrayList<>();

        // Separate tasks by priority
        for (Task task : tasks) {
            if (!task.done() && task.day() == day) {
                if (task.priority()) {
                    priorityTasks.add(task);
                } else {
                    regularTasks.add(task);
                }
            }
        }

        // Combine results with priority tasks first
        List<String> result = new ArrayList<>();
        for (Task task : priorityTasks) {
            result.add(task.code() + " " + task.name());
        }
        for (Task task : regularTasks) {
            result.add(task.code() + " " + task.name());
        }
        return result;
    }
}
