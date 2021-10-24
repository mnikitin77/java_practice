package com.mvnikitin.practice.lesson4.dao.impl;

import com.mvnikitin.practice.lesson4.Lesson4Factory;
import com.mvnikitin.practice.lesson4.dao.GenericRepository;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class GenericRepositoryImpl<T, E> implements GenericRepository<T, E> {

    private final Class<E> clazz;

    public GenericRepositoryImpl(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E save(E entity) {
        Objects.requireNonNull(entity);

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            saveOrUpdate(entity, em);
        } catch (Exception e) {
            e.printStackTrace();
        }
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public List<E> saveAll(List<E> entities) {
        Objects.requireNonNull(entities);

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        for(E entity: entities) {
            em.persist(entity);
        }
        em.getTransaction().commit();
        em.close();

        return entities;
    }

    @Override
    public void deleteById(T id) {
        Objects.requireNonNull(id);

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        E entity = em.find(clazz, id);
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteAll() {
        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM " + clazz.getSimpleName()).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public E findById(T id) {
        Objects.requireNonNull(id);

        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        E entity = em.find(clazz, id);
        em.getTransaction().commit();
        em.close();

        return entity;
    }

    @Override
    public List<E> findAll() {
        EntityManager em = Lesson4Factory.getFactory().createEntityManager();
        em.getTransaction().begin();
        List<E> entities = em.createQuery("SELECT a FROM " + clazz.getSimpleName() + " a", clazz).getResultList();
        em.getTransaction().commit();
        em.close();

        return entities;
    }

    private void saveOrUpdate(E entity, EntityManager em) throws InvocationTargetException, IllegalAccessException {
        for(Method m: entity.getClass().getMethods()) {
            if (m.getName().equalsIgnoreCase("getid")) {
                if(m.invoke(entity) == null) {
                    em.persist(entity);
                } else {
                    em.merge(entity);
                }
                break;
            }
        }
    }
}
