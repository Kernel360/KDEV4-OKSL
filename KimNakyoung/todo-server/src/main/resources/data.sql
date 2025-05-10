INSERT INTO task (title, description, status, due_date)
VALUES ('인텔리제이 강의듣기', 'ch01 끝내기', 'IN_PROGRESS', DATEADD('DAY', 1, CURRENT_TIMESTAMP));

INSERT INTO task (title, description, status, due_date)
VALUES ('인텔리제이 강의', 'ch02 끝내기', 'TODO', DATEADD('DAY', 1, CURRENT_TIMESTAMP));

-- H2는 대소문자 구별