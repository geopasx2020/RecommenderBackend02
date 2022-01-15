package RecommendationSystem.RecommenderBackend.interesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterestingService {
    @Autowired
    private InterestingRepository interestingRepository;
//
//    public Interesting findInterestingById(Long interestingId){
//        return interestingRepository.findByInterestingId(interestingId);
//    }

    public void addNewInteresting(Interesting interesting) {
        Optional<Interesting> interestingOptional=interestingRepository
                .findInterestingById(interesting.getId());

        interestingRepository.save(interesting);
    }

    }


