/*1.列出市场部(SALES)及研发部(RESEARCH)的员工*/
SELECT * FROM dept d , emp e where d.deptno = e.deptno and
(d.dname = 'SALES' or d.dname = 'RESEARCH')
/*2.列出人数超过3人的部门*/
SELECT d.dname , count(*) FROM dept d , emp e WHERE d.deptno = e.deptno GROUP BY d.dname HAVING count(*) > 3
/*3.计算MILLER年薪比SMITH高多少*/
SELECT a.a_sal , b.b_sal , a.a_sal - b.b_sal FROM
(SELECT sal * 12 a_sal FROM emp WHERE ename = 'MILLER') a,
(SELECT sal * 12 b_sal FROM emp WHERE ename = 'SMITH') b