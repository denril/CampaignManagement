package sepe.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.CampaignEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by giouri-adm on 12/6/2015.
 */

@Service("currentUserCampaignsService")
@Transactional
public class CurrentUserCampaignsService {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public CampaignEntity getCurrentUserCampaignArea()
    {
        //System.out.println("test1");
        Query query = entityManager.createQuery("FROM CampaignEntity as ua WHERE ua.registeredUsers='"+CurrentUserDetailsService.getCurrentUserEntity().getUsername()+"'");
        //System.out.println("test2");
        return (CampaignEntity) query.getSingleResult();
    }



}
