INSERT INTO USER (id, user_id, password, name, email) VALUES (1, 'choising', 'test', '최싱', '111@slipp.net');
INSERT INTO USER (id, user_id, password, name, email) VALUES (2, 'seungmin', 'test', '최승민', '222i@slipp.net');

INSERT INTO QUESTION (id, writer_id, title, contents, cur_date) VALUES (1, 1, '첫번째글', '내용없음', '2018-11-20 17:50');
INSERT INTO QUESTION (id, writer_id, title, contents, cur_date) VALUES (2, 2, '두번째글', '내용은없음', '2018-11-20 17:52');

INSERT INTO ANSWER (id, writer_id, question_id, contents, cur_date) VALUES (1, 1, 2, 'answerTest', '2018-11-21 17:52');
INSERT INTO ANSWER (id, writer_id, question_id, contents, cur_date) VALUES (2, 1, 2, 'answerTestTestTest', '2018-11-21 21:52');


