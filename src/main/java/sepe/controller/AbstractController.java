package sepe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.CampaignEntity;
import sepe.dto.CampaignDTO;
import sepe.dto.JsonResponse;
import sepe.dto.UserDTO;
import sepe.repository.CampaignRepository;
import sepe.repository.UserRepository;
import sepe.service.CampaignService;
import sepe.service.CurrentUserDetailsService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    private class StringEditor implements PropertyEditor {

        private String text;

        @Override
        public void setValue(final Object value) {
            this.text = String.valueOf(value);
        }

        @Override
        public Object getValue() {
            return this.text;
        }

        @Override
        public boolean isPaintable() {
            return false;
        }

        @Override
        public void paintValue(final Graphics gfx, final Rectangle box) {

        }

        @Override
        public String getJavaInitializationString() {
            return null;
        }

        @Override
        public String getAsText() {
            return this.text;
        }

        @Override
        public void setAsText(final String text) throws IllegalArgumentException {
            if (TODAY.equals(text)) {
                setValue(dateFormat.format(new Date()));
            } else {
                setValue(text);
            }
        }

        @Override
        public String[] getTags() {
            return new String[0];
        }

        @Override
        public Component getCustomEditor() {
            return null;
        }

        @Override
        public boolean supportsCustomEditor() {
            return false;
        }

        @Override
        public void addPropertyChangeListener(final PropertyChangeListener listener) {

        }

        @Override
        public void removePropertyChangeListener(final PropertyChangeListener listener) {

        }
    }

    public static final String ERROR_404 = "404";
    public static final String ERROR_401 = "401";
    public static final String ERROR_500 = "500";
    public static final String TODAY = "today";

    protected final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * When a controller method parameter has a default value of TODAY, the binder registration here
     * will cause the default value to be the current date in "yyyy-MM-dd" format.
     */
    @InitBinder
    public void initBinder(@Nonnull final WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(String.class, new StringEditor());
    }

    /**
     * Used by child class controllers to obtain the currently authenticated user from Spring Security.
     */
    @Nullable
    public final SpringUserDetails currentAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SpringUserDetails) {
            return (SpringUserDetails) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    @Nullable
    protected final java.sql.Date stringToSqlDate(@Nonnull final String dateString) {
        java.sql.Date date = null;
        try {
            final Date utilDate = dateFormat.parse(dateString);
            date = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
        }
        return date;
    }

    @RequestMapping(value = {"/citizenProfile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewMainCitizenProfilePage(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);

        return "citizenProfile";


    }


    @RequestMapping(value = {"/", "/profile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewMainProfilePage(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        if (user.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
            return "citizen";

        } else if (user.getIsAdmin() == 1 && user.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
            return "register";
        } else
            return ERROR_500;
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    @Nonnull
    public final String view404Page(
            @Nonnull final Model model
    ) {
        return ERROR_404;
    }

    @RequestMapping(value = {"/401"}, method = RequestMethod.GET)
    @Nonnull
    public final String view401Page(
            @Nonnull final Model model
    ) {
        return ERROR_401;
    }

    @RequestMapping(value = {"/password"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewPasswordPage(
            @Nonnull final Model model
    ) {
        return Constants.PASSWORD_TEMPLATE;
    }


    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewErrorPage(
            @Nonnull final Model model
    ) {
        return ERROR_500;
    }

    @RequestMapping(value = {"/fileupload"}, method = RequestMethod.GET)
    @Nonnull
    public final String fileupload(
            @Nonnull final Model model
    ) {
        return "fileupload";
    }

    @RequestMapping(value = {"/createCampaign"}, method = RequestMethod.GET)
    @Nonnull
    public final String createCampaign(

            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return "leafletCreateMap";
    }

    @RequestMapping(value = {"/viewCampaign"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewCampaign(

            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return "leafletViewMap";
    }

 /*   @RequestMapping(value = {"/createCampaignSuccess"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResponse createCampaignSuccess(@RequestBody CampaignDTO e) {
        System.out.println(e.getName());
        //campaignrepository.save(e);
        return new JsonResponse("OK", "");
    }
*/
 /*   @RequestMapping(value = {"/createCampaignSuccess"}, method = RequestMethod.POST)
        public @ResponseBody String createCampaignSuccess(HttpServletRequest request) {
        String message = request.getParameter("message");
        System.out.println("***Campaign Creation Successful***");
        return "Campaign Creation Successful" + " " + message;
    }

*/
    @Autowired
    public CampaignService campaignService;

    @RequestMapping(value = {"/createCampaignSuccess"}, method = RequestMethod.POST)
    public @ResponseBody CampaignDTO save( @RequestBody final CampaignDTO campaign) { //or @ResponseBody CampaignDTO post

        System.out.println("Campaign Created:"+campaign.getName());
        //CampaignService campaignService = new CampaignService();
        campaignService.createCampaign(campaign);
        return campaign;

    }


}