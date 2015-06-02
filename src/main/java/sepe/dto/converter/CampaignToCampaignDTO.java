package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sepe.domain.CampaignEntity;
import sepe.dto.CampaignDTO;

import javax.annotation.Nullable;

/**
 * Created by giouri-adm on 2/6/2015.
 */

@Component
public final class CampaignToCampaignDTO implements Converter<CampaignEntity, CampaignDTO>{

    @Override
    @Nullable
    public CampaignDTO convert(@Nullable final CampaignEntity campaign) {
        CampaignDTO dto = null;
        if (campaign != null) {
            dto = new CampaignDTO(
                   // campaign.getId(),
                    campaign.getName(),
                    campaign.getFromTime(),
                    campaign.getToTime(),
                    campaign.getExperimentsUsedId(),
                    campaign.getStatus(),
                    campaign.getMeasurements(),
                    campaign.getArea()

            );
        }
        return dto;
    }

}
