/*1.列出直接向King汇报的员工*/
SELECT * FROM emp WHERE mgr = (SELECT empno FROM emp WHERE ename = 'King')

SELECT e.* FROM emp e , (SELECT empno FROM emp WHERE ename = 'King') k WHERE e.mgr = k.empno

/*2.列出公司所有员工的工龄，并倒序排列*/
/*SELECT DATE_FORMAT(NOW(),"%Y/%m/%d")*/
SELECT * FROM (
	SELECT emp.* , DATE_FORMAT(NOW(),"%Y") - 	DATE_FORMAT(hiredate,"%Y") yg FROM emp
) d ORDER BY d.yg DESC

/*3.计算管理者与基层员工平均薪资差额*/
SELECT a_avg - b_avg FROM
(SELECT avg(sal) a_avg FROM emp where job = 'MANAGER' OR job = 'PRESIDENT') a ,
(SELECT avg(sal) b_avg FROM emp where job in ('CLERK' , 'SALESMAN' , 'ANALYST')) b







