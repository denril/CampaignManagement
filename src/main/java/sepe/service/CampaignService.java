package sepe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.*;
import sepe.dto.*;
import sepe.dto.converter.*;
import sepe.handlers.EmailExistsException;
import sepe.handlers.UsernameExistsException;
import sepe.repository.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * Created by giouri-adm on 2/6/2015.
 */

@Service
public final class CampaignService {

    @Autowired
    CampaignRepository campaignRepository;
    // @Autowired
    // CampaignToCampaignDTO campaignDTOConverter;


    public void createCampaign(
            @Nonnull final CampaignDTO campaign) {


        CampaignDTOToCampaign campaignConverter = new CampaignDTOToCampaign();
        CampaignEntity myCampaign = campaignConverter.convert(campaign);

        // campaignRepository.save(myCampaign); //problematic save
        try {
            campaignRepository.save(myCampaign);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
