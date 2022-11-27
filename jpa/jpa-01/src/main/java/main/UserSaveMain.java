package main;


import jpabasic.reserve.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class UserSaveMain {
    public static void main(String[] args){
        // EntityManagerFactory : EntityManager 생성하는 공장
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabegin");

        // EntityManager :  DB와의 연동 처리
        EntityManager entityManager = emf.createEntityManager();
        // getTransaction() :
        EntityTransaction transaction = entityManager.getTransaction();

        // 트랜잭션 시작
        try{
            transaction.begin();
            User user = new User("user@user.com", "user", LocalDateTime.now());
            entityManager.persist(user); // 영구 저장
            transaction.commit(); // 커밋
        } catch (Exception ex){
            ex.printStackTrace();
            transaction.rollback();
        }finally {
            entityManager.close();
        }

        emf.close();
    }
}
