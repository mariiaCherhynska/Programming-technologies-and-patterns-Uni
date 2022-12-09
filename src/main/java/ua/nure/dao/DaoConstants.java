package ua.nure.dao;

public class DaoConstants {
    public static String getPhones = "SELECT phone.id id_phone, phone.model, phone.processor_id, phone.display_id,\n" +
            "processor.id id_processor, processor.processor_model, processor.cores , processor.frequency,\n" +
            "display.id id_display, display.screen_refresh_rate, display.matrix_type\n" +
            "FROM phone\n" +
            "INNER JOIN processor ON phone.processor_id = processor.id \n" +
            "INNER JOIN display ON phone.display_id = display.id;";

    public static  String deletePhone = "DELETE FROM `phones`.`phone` WHERE `id` = ?;";

    public static String addPhone = "insert into phone(model, processor_id, display_id) values(?, ?, ?);";

    public static String updatePhone = "update phone set model = ?, processor_id=?, display_id =? where id = ?;";

    public static String getPhonesByModel = "SELECT phone.id id_phone, phone.model, phone.processor_id, phone.display_id,\n" +
            "processor.id id_processor, processor.processor_model, processor.cores , processor.frequency,\n" +
            "display.id id_display, display.screen_refresh_rate, display.matrix_type\n" +
            "FROM phone\n" +
            "INNER JOIN processor ON phone.processor_id = processor.id \n" +
            "INNER JOIN display ON phone.display_id = display.id where phone.model = ?;";


    public static String getPhonesById = "SELECT phone.id id_phone, phone.model, phone.processor_id, phone.display_id,\n" +
            "processor.id id_processor, processor.processor_model, processor.cores , processor.frequency,\n" +
            "display.id id_display, display.screen_refresh_rate, display.matrix_type\n" +
            "FROM phone\n" +
            "INNER JOIN processor ON phone.processor_id = processor.id \n" +
            "INNER JOIN display ON phone.display_id = display.id where phone.id = ?;";

    public static String addDisplay = "insert into display(screen_refresh_rate, matrix_type) values(?, ?);";
    public static String getDisplay = "SELECT display.id id_display, display.screen_refresh_rate, display.matrix_type FROM display;";

    public static String addProcessor = "insert into processor(model, cores, frequency) values(?, ?, ?);";
    public static String getProcessor = "SELECT processor.id id_processor, processor.processor_model, processor.cores , processor.frequency FROM processor;";

    static public String getRoles = "SELECT * FROM `role`;";

    static public String registerUser = """
            INSERT INTO `user` (username, role_id)
            VALUES (?, (SELECT `id` FROM `role` WHERE
            `role` = ?));
            """;
    static public String userAuthorize = """
            SELECT `user`.id id_user, user.username, user.role_id, role.id id_role, role.role FROM `user`\s
            INNER JOIN `role`\s
            ON `user`.`role_id` = `role`.`id`
            WHERE `user`.`username` = ?;
            """;
}
