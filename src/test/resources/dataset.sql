

SELECT sysdate FROM dual;

MERGE INTO PESSOA (ID, APELIDO, NOME, NASCIMENTO, STACK)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'CACHORRO','NINA','1985-12-01','PHP,JAVA,KOTLIN');
