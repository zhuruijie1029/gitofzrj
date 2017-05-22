	create table person
	(
	   person_id int not null auto_increment,
	   person_name varchar(50),
	   person_age int,
	   primary key (person_id)
	);
	
	insert into person (person_id, person_name, person_age)
		values(null, 'Tom', 10);

	insert into person ( person_name, person_age)
		values( 'Jerry', 10);	
		
select * from person;
		
select      
u.cn_user_name as name,    
n.cn_note_title as title,    
n.cn_note_id  as noteId   
from  cn_note n  left join  cn_user u on
n.cn_user_id = u.cn_user_id     
WHERE 
(n.cn_note_body like '%a%' 
or n.cn_note_title like '%a%') 


select 
count(cn_notebook_id) ,
cn_notebook_id
from cn_note 
group by cn_notebook_id








		
	