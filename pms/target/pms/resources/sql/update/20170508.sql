-- 部门表的level现已被用作"部门所属公司的ID"，总部部门或者服务多个公司的业务部门的话这个字段值为0就行了。
-- 下面的update先把所有的部门所属公司设置为0，然后再用admin用户去后台设置一下个别部门的所属公司。

update department set level = '0';