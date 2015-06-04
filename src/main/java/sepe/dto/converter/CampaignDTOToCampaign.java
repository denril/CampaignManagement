package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import sepe.domain.CampaignEntity;
import sepe.dto.CampaignDTO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.security.SecureRandom;

/**
 * Created by giouri-adm on 2/6/2015.
 */

@Component
public final class CampaignDTOToCampaign implements Converter<CampaignDTO, CampaignEntity>{

    @Override
    @Nullable
    public CampaignEntity convert(@Nullable final CampaignDTO campaignDTO) {
        CampaignEntity campaign = null;
        if (campaignDTO != null) {
            campaign = new CampaignEntity(
                  //  not needed since id column is auto generated // campaignDTO.getId(),
                    campaignDTO.getName(),
                    campaignDTO.getFromTime(),
                    campaignDTO.getToTime(),
                    campaignDTO.getExperimentsUsedId(),
                    campaignDTO.getStatus(),
                    campaignDTO.getMeasurements(),
                    campaignDTO.getArea()
            );
        }
        return campaign;
    }

}
