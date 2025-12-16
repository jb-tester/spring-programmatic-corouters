-- Students
INSERT INTO students (first_name, last_name, email) VALUES
('John', 'Doe', 'john.doe@university.edu'),
('Jane', 'Smith', 'jane.smith@university.edu'),
('Michael', 'Johnson', 'michael.johnson@university.edu'),
('Emily', 'Williams', 'emily.williams@university.edu'),
('David', 'Brown', 'david.brown@university.edu'),
('Sarah', 'Davis', 'sarah.davis@university.edu'),
('Robert', 'Miller', 'robert.miller@university.edu'),
('Lisa', 'Wilson', 'lisa.wilson@university.edu');

-- Courses
INSERT INTO courses (name, code, credits) VALUES
('Introduction to Computer Science', 'CS101', 3),
('Data Structures and Algorithms', 'CS201', 4),
('Database Management Systems', 'CS301', 3),
('Web Development', 'CS250', 3),
('Operating Systems', 'CS302', 4),
('Software Engineering', 'CS350', 3),
('Computer Networks', 'CS303', 3),
('Machine Learning', 'CS401', 4),
('Calculus I', 'MATH101', 4),
('Linear Algebra', 'MATH201', 3);

-- Student Course Enrollments (some with passed exams, some without)
INSERT INTO student_courses (student_id, course_id, enrollment_date, exam_passed, grade, exam_date) VALUES
-- John Doe's courses
(1, 1, '2024-09-01', true, 85, '2024-12-15'),
(1, 2, '2024-09-01', true, 92, '2024-12-16'),
(1, 9, '2024-09-01', false, null, null),

-- Jane Smith's courses
(2, 1, '2024-09-01', true, 78, '2024-12-15'),
(2, 3, '2024-09-01', true, 88, '2024-12-17'),
(2, 4, '2024-09-01', true, 95, '2024-12-18'),
(2, 10, '2024-09-01', false, null, null),

-- Michael Johnson's courses
(3, 2, '2024-09-01', true, 81, '2024-12-16'),
(3, 5, '2024-09-01', false, null, null),
(3, 9, '2024-09-01', true, 76, '2024-12-14'),

-- Emily Williams's courses
(4, 1, '2024-09-01', true, 90, '2024-12-15'),
(4, 4, '2024-09-01', true, 87, '2024-12-18'),
(4, 6, '2024-09-01', false, null, null),

-- David Brown's courses
(5, 3, '2024-09-01', true, 84, '2024-12-17'),
(5, 7, '2024-09-01', false, null, null),
(5, 10, '2024-09-01', true, 79, '2024-12-19'),

-- Sarah Davis's courses
(6, 2, '2024-09-01', true, 93, '2024-12-16'),
(6, 8, '2024-09-01', false, null, null),
(6, 9, '2024-09-01', true, 88, '2024-12-14'),

-- Robert Miller's courses
(7, 1, '2024-09-01', true, 72, '2024-12-15'),
(7, 5, '2024-09-01', true, 80, '2024-12-20'),
(7, 6, '2024-09-01', false, null, null),

-- Lisa Wilson's courses
(8, 4, '2024-09-01', true, 91, '2024-12-18'),
(8, 7, '2024-09-01', true, 86, '2024-12-21'),
(8, 8, '2024-09-01', false, null, null);
