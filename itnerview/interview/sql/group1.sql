/*1.按部门编号升序、工资降序的排列员工信息*/
SELECT * FROM emp ORDER BY deptno ASC , sal DESC
/*2.列出deptno=30的部门名称及员工*/
SELECT dept.dname , emp.* FROM emp , dept WHERE emp.deptno = dept.deptno and dept.deptno = 30
/*3.列出每个部门最高、最低及平均工资*/
SELECT deptno , max(sal) , min(sal) , avg(sal) , count(*)  FROM emp GROUP BY deptno