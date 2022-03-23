package RecommendationSystem.RecommenderBackend.pois;

import RecommendationSystem.RecommenderBackend.categories.Category;
import RecommendationSystem.RecommenderBackend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PoiRepository extends JpaRepository<Poi,Long> {
   //Page<Poi> findById(@RequestParam("id") Long id, Pageable pageable);
   //List<Poi> findByCategoryIn(Collection<Interesting> categories);  //select p from Poi where p.categoru in :categories

   //List<Poi> findByCategoryIn(Collection<Category> categories);  //select p from Poi where p.categoru in :categories
   List<Poi> findByCategoryInOrderByAverageScore(Collection<Category> categories);  //select p from Poi where p.categoru in :categories

   //@Query("SELECT u FROM User u WHERE year(u.dob) between ?1 and ?2")
   //@Query("select p from Poi p inner join Review r WHERE r.user in ?1 ")
   @Query("select distinct p from Review r inner join r.poi as p WHERE r.user in ?1 ORDER by p.averageScore")
   List<Poi> findByUsersReviews(Collection<User> users);  //select p from Poi where p.categoru in :categories


   //em.createQuery("select distinct p from Review r inner join r.poi as p WHERE r.user in ?1 AND USER.AGE= ?2 ORDER by r.score").bindParameter("users", users).execute()
}
