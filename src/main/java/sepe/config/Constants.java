package sepe.config;

import java.sql.Timestamp;

/**
 * Created by Evangelos on 23/1/2015.
 */
public class Constants {

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String LOGIN_TEMPLATE = "login";
    public static final String REGISTER_TEMPLATE = "register";
    public static final String CITIZEN_PROFILE_TEMPLATE = "citizenProfile";
    public static final String PASSWORD_TEMPLATE = "password";



    /* ---------------------------------------------------------------------
    USER_TYPE
     --------------------------------------------------------------------- */
    public enum USER_TYPE {
        ADMIN("ROLE_ADMIN", 0),
        CITIZEN("ROLE_CITIZEN",1),
        UNKNOWN("UNKNOWN", 2);


        private final String name;
        private final int code;


        private USER_TYPE(String name, int code) {
            this.name = name;
            this.code = code;

        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public Role getRole() {
            return getUserRole(this.code);
        }

        public static USER_TYPE getUserType(String value) {
            if (value.equals(USER_TYPE.CITIZEN.getName()))
                return USER_TYPE.CITIZEN;
            else if (value.equals(USER_TYPE.ADMIN.getName()))
                return USER_TYPE.ADMIN;
            else {
                return USER_TYPE.UNKNOWN;
            }
        }

        public static USER_TYPE getUserType(int value) {
            if (value == (USER_TYPE.CITIZEN.getCode()))
                return USER_TYPE.CITIZEN;

            else if (value == (USER_TYPE.ADMIN.getCode()))
                return USER_TYPE.ADMIN;

            else {
                return USER_TYPE.UNKNOWN;
            }
        }

        public static Role getUserRole(int value) {
            if (value == (USER_TYPE.CITIZEN.getCode()))
                return new Role(USER_TYPE.CITIZEN.getName(), USER_TYPE.CITIZEN.getCode());
             else if (value == (USER_TYPE.ADMIN.getCode()))
                return new Role(USER_TYPE.ADMIN.getName(), USER_TYPE.ADMIN.getCode());
            else {
                return null;
            }
        }
    }





    public static boolean StringIsNullOrEmpty(String s) {
        if (s == null) return true;
        if (s.isEmpty()) return true;
        return false;
    }

    public static boolean IntegerIsNullOrEmpty(Integer i) {
        if (i == null) return true;
        return false;
    }

    public static boolean LongIsNullOrEmpty(Long i) {
        if (i == null) return true;
        return false;
    }

    public static boolean TimestampIsNullOrEmpty(Timestamp t) {
        if (t == null) return true;
        return false;
    }
}
