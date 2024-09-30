package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("select a from User a join fetch a.car");
      return query.getResultList();
   }

   @Override
   public List<User> getUserByModelAndSeries(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery("select a from User a join fetch a.car b where b.model = :model and b.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
   }
}
