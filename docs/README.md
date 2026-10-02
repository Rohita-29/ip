# Nimbus User Guide

**Sunglasses on. Briefcase loaded. Let's do business.**

Nimbus is a command-line task manager. It keeps track of todos,
deadlines, and events, and saves your tasks between sessions.

## Quick start

1. Install Java 25.
2. Download `ip.jar` from the
   [latest release](https://github.com/Rohita-29/ip/releases/latest).
3. Place it in a folder where you have permission to create files.
4. Open a terminal in that folder and run:

   ```bash
   java -jar ip.jar
   ```

5. Enter one command at a time. Type `bye` to exit.

## Command summary

| Command | Format |
|---------|--------|
| Add a todo | `todo <description>` |
| Add a deadline | `deadline <description> /by <date or time>` |
| Add an event | `event <description> /from <start> /to <end>` |
| List tasks | `list` |
| Find tasks | `find <keyword>` |
| Mark completed | `mark <task number>` |
| Mark incomplete | `unmark <task number>` |
| Delete a task | `delete <task number>` |
| Exit | `bye` |

Do not type the angle brackets. Commands are lowercase.

## Adding a todo

A todo is a task without an attached date or time.

```text
todo read book
```

Nimbus adds the task and displays the number of tasks in your list.

## Adding a deadline

A deadline is a task with a due date or time.

```text
deadline submit assignment /by Friday 11:59pm
```

The description and the field after `/by` must not be empty.
Dates and times are stored as text; Nimbus does not validate calendar
dates or send reminders.

## Adding an event

An event has a start and end.

```text
event project meeting /from Friday 2pm /to Friday 4pm
```

The description, start, and end must all be provided. Keep spaces
around `/from` and `/to`. Times are stored as text.

## Listing tasks

```text
list
```

Tasks are numbered from 1. For example:

```text
1. [T][ ] read book
2. [D][X] submit assignment (by: Friday 11:59pm)
3. [E][ ] project meeting (from: Friday 2pm to: Friday 4pm)
```

`T`, `D`, and `E` identify todos, deadlines, and events.
`[X]` means completed; `[ ]` means incomplete.

## Finding tasks

```text
find book
```

Nimbus displays tasks whose descriptions contain the search text.
Matching ignores case, so `find BOOK` also matches `read book`.

Results retain their original task numbers from `list`.
An empty keyword produces an error; no matches produces a message.

## Marking and unmarking tasks

```text
mark 1
```

Marks task 1 as completed.

```text
unmark 1
```

Marks task 1 as incomplete. Use the task number shown by `list`
or `find`.

## Deleting tasks

```text
delete 1
```

Permanently removes task 1. There is no undo command.
Later tasks are renumbered, so run `list` again before selecting
another task.

## Saving and loading

Nimbus automatically saves after adding, marking, unmarking,
or deleting a task.

Tasks are stored in `data/nimbus.txt`, relative to the folder from
which you run the app. Nimbus creates the data folder when needed
and loads saved tasks when it starts.

Running the app from another folder uses a different data file.
Keep the same working folder to continue using your existing list.

If Nimbus reports a saving error, your latest changes may not have
been saved. Check that the folder is writable.

## Errors and input restrictions

- Task numbers must be integers corresponding to existing tasks.
- Descriptions and required time fields cannot be empty.
- Task fields cannot contain ` | ` because it is reserved for storage.
- Use the command formats shown above.
- Avoid editing the saved data file manually; malformed records may
  prevent Nimbus from starting.

## Exiting

```text
[mic drop]

Instructions handled. Exit executed. Nimbus rocked!
Too much work done today. Need my beauty sleep. BYEEEEEE!!!
```

Nimbus displays its exit message and closes.
Unfinished tasks remain available when you next run it from the
same folder.