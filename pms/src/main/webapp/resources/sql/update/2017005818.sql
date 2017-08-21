-- 最近的操作中删除menu表中的数据role_menu的数据出现脏数据，所以增加了两个级联操作，优化sql性能防止脏数据产生

alter table role_menu add constraint fk_rolemenu_menu foreign key(menu_id) references menu(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table role_menu add constraint fk_rolemenu_role foreign key(role_id) references role(id) ON DELETE CASCADE ON UPDATE CASCADE;