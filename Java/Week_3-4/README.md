# java-ps2-collections — PS-2 V2 (Java Collections + Streams)

## What this is
PS-2 (8 CSVs, 10 queries) implemented with:
- `HashMap` for O(1) lookups instead of O(n²) nested loops
- `ArrayList` for dynamic collections
- Java Streams API for declarative querying
- `Optional` for null safety

## Architecture
```
DataLoader  →  Indexes  →  QueryEngine  →  Main
(reads CSVs)  (builds     (runs queries   (prints
              HashMaps)    via streams)     results)
```

## Filesystem
```
java-ps2-collections/
├── data/                          ← symlink or copy your 8 CSVs here
├── src/main/java/university/
│   ├── Main.java
│   ├── models/
│   │   ├── Department.java
│   │   ├── Stream.java
│   │   ├── Student.java
│   │   ├── Subject.java
│   │   ├── Mark.java
│   │   ├── Teacher.java
│   │   ├── ClassSession.java
│   │   └── TeacherAssignment.java
│   ├── loader/
│   │   └── DataLoader.java
│   ├── indexes/
│   │   └── Indexes.java
│   └── queries/
│       └── QueryEngine.java
├── out/                           ← compiled .class files (git-ignored)
└── compile_and_run.sh
```

## How to run
```bash
# From project root:
bash compile_and_run.sh path/to/data

# Or with default 'data/' folder:
bash compile_and_run.sh
```

## The 10 Queries
| # | Query |
|---|-------|
| Q1 | Highest score in a subject + student |
| Q2 | Teacher who taught a subject |
| Q3 | Avg/Max/Min for a subject |
| Q4 | Department-wise student count |
| Q5 | Department with most class sessions |
| Q6 | Full student report (subjects + marks) |
| Q7 | All teachers in a dept + their subjects |
| Q8 | Busiest day of the week |
| Q9 | Student ranking by overall % |
| Q10 | Teachers teaching > 3 subjects |

## Key V2 concepts used
| Concept | Where |
|---------|-------|
| `Collectors.toMap()` | Indexes — build primary key maps |
| `Collectors.groupingBy()` | Indexes — build 1-to-many maps |
| `Collectors.summarizingInt()` | Q3 — avg/min/max in one pass |
| `Optional` | Q1, Q5, Q8 — safe max/min |
| `Comparator.comparingInt().reversed()` | Q4, Q9 — sorted output |
| `stream().distinct()` | Q2, Q10 — deduplicate |
| `stream().limit()` | Q9 — top N |
