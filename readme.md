# Software Engineering Learning Curriculum

**Duration:** 3 Months (Starting March 2026)  
**Goal:** Build a strong foundation for an IT Software Engineering career  
**Prepared:** March 16, 2026

---

# Guiding Principles

## How You Will Use AI (Non-Negotiable)

AI (ChatGPT, Claude, etc.) is your **teacher, not your coder**.

Rules:

- Never copy-paste AI generated code into projects
- Always write code yourself from understanding
- Use AI like a mentor, not a shortcut

Example questions to ask AI:

- Explain conceptually how a HashMap works internally
- What are the trade-offs of ArrayList vs LinkedList?
- Why would this approach fail for large datasets?
- What are better alternatives and why?
- What is wrong with this approach?

After writing your own code:
- You may ask AI for review
- Improve code yourself
- Focus on building mental models

Goal: **Develop thinking ability, not output speed** :contentReference[oaicite:0]{index=0}

---

# How You Will Use LeetCode

LeetCode runs in parallel across all 3 months.

Guidelines:

- Solve **2–3 easy problems weekly**
- Try multiple approaches before solutions
- Focus on understanding why solutions work

Categories to rotate:

- Arrays
- Strings
- HashMaps
- Sorting
- Linked Lists
- Stacks & Queues
- Basic Trees

Track:
- What you attempted
- What you learned
- Mistakes made

Not just solved problems. :contentReference[oaicite:1]{index=1}

---

# Month 1 — Building the Foundation

## Theme
Core Programming + Data Structures using Real-World University Data

---

# Week 1–2: Core Java & Python Fundamentals

## Setup

Install:

- JDK 17+
- Python 3.11+
- VS Code or IntelliJ Community
- Git repository (start Day 1)

Important:
Commit every meaningful change.

---

## Concepts to Solidify

Learn in **both Java and Python**:

### Programming Basics

- Variables
- Data types
- Type casting
- Conditionals (if/else/switch)
- Loops (for, while, foreach)

### Functions

- Methods
- Parameters
- Return types
- Scope

### Data Handling

- Arrays
- String operations
- File I/O
- Reading text files
- Reading CSV

### OOP

- Classes
- Objects
- Constructors
- Encapsulation

### Error Handling

Java:
```
try-catch
```

Python:
```
try-except
```

---

# Week 2–3: University Data System Project

## Step 1 — Generate Synthetic Data

Use AI only to generate **data**, not code.

Create CSV datasets (~100 records each):

| # | CSV File | Key Columns |
|---|----------|-------------|
| 1 | departments.csv | dept_id, dept_name, building, head_of_dept |
| 2 | streams.csv | stream_id, stream_name, dept_id |
| 3 | students.csv | student_id, name, stream_id, dept_id, enrollment_year |
| 4 | subjects.csv | subject_id, subject_name, dept_id, credits |
| 5 | student_marks.csv | student_id, subject_id, semester, marks_obtained, max_marks |
| 6 | teachers.csv | teacher_id, name, dept_id, specialization |
| 7 | class_sessions.csv | session_id, subject_id, teacher_id, day_of_week, start_time, end_time, room |
| 8 | teacher_assignments.csv | teacher_id, subject_id, semester |

---

# Step 2 — Build Entity Models

Create classes in Java and Python:

Example:

Student object:
- id
- name
- stream
- department
- enrollment year

Mark object:
- student reference
- subject reference
- semester
- marks

Load CSV rows into:

Java:
- Arrays

Python:
- Lists

---

## Rules for Phase 1

### Java Restrictions

Use only:

- Arrays
- String.split()
- BufferedReader

Do NOT use:

- ArrayList
- HashMap
- Stream API

### Python Restrictions

Use only:

- Lists
- Dictionaries
- Loops
- open()
- split()

Do NOT use:

- pandas
- csv module
- list comprehensions (initially)

---

# Step 3 — Write Query Programs

Implement each as separate functions:

## Queries

1. What is the highest score in a subject? Who got it?
2. Which teacher taught that subject?
3. Average, maximum and minimum score per subject
4. Department-wise student count
5. Which department has the most classroom sessions?
6. List subjects a student is enrolled in with marks
7. Find teachers in a department and their subjects
8. Which weekday has the most classes?
9. Rank students by overall percentage
10. Find teachers teaching more than 3 subjects

---

# Data Structures Learning Outcome

Through solving:

You will naturally learn:

### Core Structures

- Arrays
- Searching
- Sorting
- Nested loops

### Practical Skills

- CSV parsing
- Data joins
- String manipulation
- Output formatting

---

# Week 3–4: Redo Using Advanced Tools

Now solve same problems using better tools.

---

# Java Advanced Tools

Use:

- ArrayList
- HashMap
- HashSet
- Collections.sort()
- Comparator

Learn Streams:

- filter()
- map()
- collect()
- groupingBy()
- summarizingInt()

Learn:

- Optional class

---

# Python Advanced Tools

Use:

### Functional Tools

- List comprehensions
- sorted()
- filter()
- map()

### Collections

- Counter
- defaultdict

### Pandas Fundamentals

Learn:

```
pd.read_csv()
df.head()
df.describe()
df.info()
```

Filtering:

```
df[condition]
```

Aggregation:

```
df.groupby()
```

Joining:

```
df.merge()
```

Transformation:

```
df.sort_values()
df.apply()
```

Summarization:

```
df.pivot_table()
```

Important insight:

Pandas can replace most Excel workflows programmatically. :contentReference[oaicite:2]{index=2}

---

# Month 1 Checkpoint

By end of Month 1 you should be able to:

- Read CSV manually into objects
- Write data processing logic without libraries
- Use Java Collections fluently
- Use Pandas for data manipulation
- Explain:

Difference between:

- Array
- ArrayList
- HashMap
- HashSet

Also:

- Maintain clean Git repository
- Meaningful commits

---

# Month 2 — Industry Grade Data & Databases

## Theme

E-commerce data + Databases

---

# E-commerce Dataset

Entities:

| Entity | Key Attributes |
|--------|---------------|
| Customers | customer_id, name, email, address, registration_date |
| Products | product_id, name, category, price, stock_quantity |
| Orders | order_id, customer_id, order_date, status, total_amount |
| Order Items | order_id, product_id, quantity, unit_price |
| Payments | payment_id, order_id, payment_method, amount, status |
| Promotions | promo_id, code, discount_type, discount_value |
| Deliveries | delivery_id, order_id, status, estimated_date |

---

# Database Learning

## PostgreSQL

Learn:

- CREATE TABLE
- INSERT
- SELECT
- JOIN
- GROUP BY
- HAVING
- ORDER BY

Advanced:

- Subqueries
- Aggregations
- Window functions

Integration:

Java:
- JDBC

Python:
- psycopg2

---

# Redis (NoSQL)

Learn:

- When to use Redis vs PostgreSQL

Redis structures:

- Strings
- Lists
- Sets
- Hashes
- Sorted Sets

Use cases:

- Caching
- Sessions
- Leaderboards
- Rate limiting

Libraries:

Java:
- Jedis

Python:
- redis-py

---

# Month 2 Problem Statements

Examples:

- Top selling products
- Customer lifetime value
- Order fulfillment rate
- Revenue trends
- Promotion effectiveness
- Customer churn risk
- Inventory recommendations

---

# Month 3 — Full Stack Development

## Theme

Build real application.

---

# Backend Microservices

## Java Spring Boot

Learn:

- REST APIs
- Controllers
- Services
- Repositories
- DTOs
- CRUD operations

Concepts:

- HTTP lifecycle
- Status codes
- Request/Response

---

## Python FastAPI

Learn:

- Route decorators
- Pydantic models
- Dependency injection
- Async fundamentals

Connect both services to same database.

Important lesson:

**Database is independent from application code**

---

# Frontend — React

Learn:

- HTML
- CSS
- JavaScript

Build:

University dashboard:
- Student records
- Department stats
- Timetable

E-commerce UI:
- Products
- Orders
- Customer dashboard

Learn:

- Components
- Props
- State
- Hooks
- API calls

---

# Architecture Overview

```
React Frontend
      |
HTTP REST
      |
Spring Boot API ←→ PostgreSQL ←→ FastAPI
      |
      Redis Cache
```

---

# Weekly Rhythm

| Day | Activity |
|-----|----------|
| Mon-Fri | 2–3 hours coding |
| Saturday | 1 hour LeetCode |
| Sunday | Review + cleanup |

---

# Tools

| Tool | Purpose |
|------|---------|
| VS Code / IntelliJ | IDE |
| GitHub | Version control |
| PostgreSQL | Database |
| Redis | Cache |
| Node.js | React |
| Postman | API testing |
| AI tools | Learning + review only |

---

# Final Note

This curriculum builds skills from the ground up.

Avoid shortcuts.

Strong engineers are those who:

- Think through problems
- Understand tradeoffs
- Write clean code
- Build from fundamentals

Every line written manually in Month 1 helps you succeed in Month 3.

**Trust the process.** :contentReference[oaicite:3]{index=3}

---
