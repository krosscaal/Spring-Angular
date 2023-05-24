create table  if not exists task (
  id bigserial not null,
  feito boolean,
  name varchar(255) not null ,
  primary key (id)
)
