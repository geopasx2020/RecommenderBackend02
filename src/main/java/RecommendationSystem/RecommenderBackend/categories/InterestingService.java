package RecommendationSystem.RecommenderBackend.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterestingService {
    @Autowired
    private CategoryRepository categoryRepository;
//
//    public Interesting findInterestingById(Long interestingId){
//        return interestingRepository.findByInterestingId(interestingId);
//    }

    public void addNewInteresting(Category category) {
        Optional<Category> interestingOptional= categoryRepository
                .findInterestingById(category.getId());

        categoryRepository.save(category);
    }

    }


