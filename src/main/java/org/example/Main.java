package org.example;

import org.example.entities.PublicationEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf=null;
        EntityManager em=null;

        try{
            emf= Persistence.createEntityManagerFactory("default");
            em=emf.createEntityManager();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Entity Manager created ("+emf+")");
            em.getTransaction().begin();

            PublicationEntity p=new PublicationEntity();
            p.setTitle("Title");
            p.setIsbn10("isbn1234");

            em.persist(p);

            em.getTransaction().commit();

            int id=p.getId();

            // get all entities
            List<PublicationEntity> ListOfPublicationEntitys = em.createQuery("SELECT c FROM PublicationEntity c").getResultList();
            System.out.println("\n------------------\nList of PublicationEntitys\n------------------");
            for(PublicationEntity PublicationEntity:ListOfPublicationEntitys){
                System.out.println(PublicationEntity.getTitle()+ " "+ PublicationEntity.getIsbn10());
            }

            // demonstrate editing of an entity
            //
            System.out.println("\n------------------\nFind and Edit a Publication\n------------------");
            em.getTransaction().begin(); // must wrap with beigin and commit or it wont work
            PublicationEntity p2=em.find(PublicationEntity.class, id);
            p.setTitle("Edited Title");
            p.setIsbn10("Edited isbn1234");
            em.merge(p2);
            em.getTransaction().commit();

            ListOfPublicationEntitys = em.createQuery("SELECT c FROM PublicationEntity c").getResultList();
            System.out.println("\n------------------\nList of PublicationEntitys\n------------------");
            for(PublicationEntity PublicationEntity:ListOfPublicationEntitys){
                System.out.println(PublicationEntity.getTitle()+ " "+ PublicationEntity.getIsbn10());
            }

            // add a new Publication
            System.out.println("\n------------------\nAdd a Publication\n------------------");
            PublicationEntity p3=new PublicationEntity();
            p.setTitle("Title");
            p.setIsbn10("isbn1234");
            em.getTransaction().begin();
            em.persist(p3);
            em.getTransaction().commit();

            // confirm added
            ListOfPublicationEntitys = em.createQuery("SELECT c FROM PublicationEntity c").getResultList();
            System.out.println("\n------------------\nList of PublicationEntitys\n------------------");
            for(PublicationEntity PublicationEntity:ListOfPublicationEntitys){
                System.out.println(PublicationEntity.getTitle()+ " "+ PublicationEntity.getIsbn10());
            }
            // delete Publication
            System.out.println("\n------------------\nDelete a Publication\n------------------");
            em.getTransaction().begin();
            em.remove(p3);
            em.getTransaction().commit();
            // confirm deleted
            ListOfPublicationEntitys = em.createQuery("SELECT c FROM PublicationEntity c").getResultList();
            System.out.println("\n------------------\nList of PublicationEntitys\n------------------");
            for(PublicationEntity PublicationEntity:ListOfPublicationEntitys){
                System.out.println(PublicationEntity.getTitle()+ " "+ PublicationEntity.getIsbn10());
            }

        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(emf!=null)
                emf.close();
//            if(em!=null)
//                em.close();
        }
    }

}
