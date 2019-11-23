-- INSERT INTO users SELECT * FROM (

-- select '1','admin','admin'

-- ) X WHERE NOT EXISTS(SELECT * FROM users);


-- INSERT INTO page_list SELECT * FROM (
-- select'1','categories.jsp','Categories','2'UNION
-- select'2','item.jsp','Item','2'UNION
-- select'3','invoice.jsp','Invoice','2'UNION
-- select'4','companies.jsp','Company','3'UNION
-- select'5','department.jsp','Department','3'UNION
-- select'6','employee.jsp','Employee','3'UNION
-- select'7','users.jsp','Users','6'UNION

-- select'8','categories.jsp','Categories','8'UNION
-- select'9','item.jsp','Item','8'UNION
-- select'10','invoice.jsp','Invoice','8'UNION
-- select'11','department.jsp','Department','9'UNION
-- select'12','employee.jsp','Employee','9'UNION
-- select'13','usersex.jsp','Users','12'UNION

-- select'14','categories.jsp','Categories','14'UNION
-- select'15','item.jsp','Item','14'UNION
-- select'16','invoice.jsp','Invoice','14'UNION
-- select'17','department.jsp','Department','15'UNION
-- select'18','employee.jsp','Employee','15'


-- ) X WHERE NOT EXISTS(SELECT * FROM page_list);

-- INSERT INTO users (user_id, user_mobile,user_name,user_parent_id,user_password,user_real_name,user_role)
--   SELECT '1','0756140065','admin','1','admin','admin','ADMIN'
-- WHERE NOT EXISTS (SELECT user_id FROM users WHERE user_id = '1');

INSERT INTO users (user_id,password,user_role)
  SELECT '1','admin','admin'
WHERE NOT EXISTS (SELECT user_id FROM users WHERE user_id = '1');

INSERT INTO class (id,grade,name)
  SELECT '1','0','null'
WHERE NOT EXISTS (SELECT id FROM class WHERE id = '1');
