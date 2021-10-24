package com.mvnikitin.practice.lesson4.dao.impl;

import com.mvnikitin.practice.lesson4.Lesson4Factory;
import com.mvnikitin.practice.lesson4.dao.StudentRepository;
import com.mvnikitin.practice.lesson4.entity.Student;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public Student save(Student entity) {
        Objects.requireNonNull(entity);

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        if(entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public List<Student> saveAll(List<Student> entities) {
        Objects.requireNonNull(entities);

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        for(Student entity: entities) {
            em.persist(entity);
        }
        em.getTransaction().commit();
        em.close();

        return entities;
    }

    @Override
    public void deleteById(Long id) {
        Objects.requireNonNull(id);

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        Student entity = em.find(Student.class, id);
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }


    @Override
    public void deleteAll() {
        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("TRUNCATE TABLE student").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Student findById(Long id) {
        Objects.requireNonNull(id);

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        Student entity = em.find(Student.class, id);
        em.getTransaction().commit();
        em.close();

        return entity;
    }

    @Override
    public List<Student> findAll() {

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        List<Student> entities = em.createQuery("SELECT a FROM Student a", Student.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return entities;
    }
}
