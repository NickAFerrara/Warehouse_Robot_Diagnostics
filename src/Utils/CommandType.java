package Utils;
public enum CommandType {
    
    /* Read cmd range 0x00 to 0x63 (100 cmds) */
    SW_CMD_INVALID("SW_CMD_INVALID", (byte)0x00),
    SW_R_FW_VERSION("SW_R_FW_VERSION", (byte)0x01),
    SW_R_LINUX_INFO("SW_R_LINUX_INFO", (byte)0x02),
    SW_R_MAC_ADDRESS("SW_R_MAC_ADDRESS", (byte)0x03),
    SW_GET_POD_ID("SW_GET_POD_ID", (byte)0x04),
    SW_R_SYS_TIME("SW_R_SYS_TIME", (byte)0x05),
    
    SW_GET_ROBOT_LOCATION("SW_GET_ROBOT_LOCATION", (byte)0x06),
    SW_GET_ROBOT_STATUS("SW_GET_ROBOT_STATUS", (byte)0x07),
    SW_MOTOR_HANDSHAKE("SW_MOTOR_HANDSHAKE", (byte)0x08),
    SW_MOTOR_PWM_STEPS("SW_MOTOR_PWM_STEPS", (byte)0x09),
    SW_MOTOR_START("SW_MOTOR_START", (byte)0x0A),
    SW_MOTOR_STOP("SW_MOTOR_STOP", (byte)0x0B),
    SW_SET_MOTOR_DIR("SW_SET_MOTOR_DIR", (byte)0x0C),
    SW_MODIFY_MOTOR_RPM("SW_MODIFY_MOTOR_RPM", (byte)0x0D),
    SW_MOVE_STRAIGHT("SW_MOVE_STRAIGHT", (byte)0x0E),
    SW_IS_MOTOR_STOPPED("SW_IS_MOTOR_STOPPED", (byte)0x0F),
    SW_TURN_90_CLOCK_WISE("SW_TURN_90_CLOCK_WISE", (byte)0x10),
    SW_TURN_90_COUNTER_CLOCK_WISE("SW_TURN_90_COUNTER_CLOCK_WISE", (byte)0x11),
    SW_TURN_180_CLOCK_WISE("SW_TURN_180_CLOCK_WISE", (byte)0x12),
    SW_TURN_180_COUNTER_CLOCK_WISE("SW_TURN_180_COUNTER_CLOCK_WISE", (byte)0x13),
    SW_GET_ROBOT_ID("SW_GET_ROBOT_ID", (byte)0x14),
    SW_MOTOR_ROTATE_TT("SW_MOTOR_ROTATE_TT", (byte)0x15),
    SW_MOTOR_LIFT_UP("SW_MOTOR_LIFT_UP", (byte)0x16),
    SW_MOTOR_LIFT_DOWN("SW_MOTOR_LIFT_DOWN", (byte)0x17),
    SW_CAMERA_READ_INFO("SW_CAMERA_READ_INFO", (byte)0x18),
    SW_LOWER_LIFT_MOTOR("SW_LOWER_LIFT_MOTOR", (byte)0x19),
    SW_SEL_CAM("SW_SEL_CAM", (byte)0x1A),
    SW_POWER_CYCLE_BOT("SW_POWER_CYCLE_BOT", (byte)0x1B),
    SW_GET_BAT_STATUS("SW_GET_BAT_STATUS", (byte)0x1C),
    SW_ADJUST_FLOOR_XY("SW_ADJUST_FLOOR_XY", (byte)0x1D),
    SW_ADJUST_CEIL_XY("SW_ADJUST_CEIL_XY", (byte)0x1E),
    SW_MOTOR_APP_BOOT("SW_MOTOR_APP_BOOT", (byte)0x1F),
    SW_MOTOR_BL_BOOT("SW_MOTOR_BL_BOOT", (byte)0x20),
    SW_FLASH_MOTOR_FW("SW_FLASH_MOTOR_FW", (byte)0x21),
    SW_RESET_MOTOR_MCU("SW_RESET_MOTOR_MCU", (byte)0x22),
    
    /* P71 commands */
    SW_P71_CONFIG_READ("SW_P71_CONFIG_READ", (byte)0x23),
    SW_P71_CONFIG_WRITE("SW_P71_CONFIG_WRITE", (byte)0x24),
    SW_P71_MOTION_CMD("SW_P71_MOTION_CMD", (byte)0x25),
    SW_P71_STATUS_READ("SW_P71_STATUS_READ", (byte)0x26),
    
    SW_P71_ROBOT_MOVE("SW_P71_ROBOT_MOVE", (byte)0x27),
    SW_P71_ROBOT_TURN("SW_P71_ROBOT_TURN", (byte)0x28),
    SW_P71_ROBOT_USER_TURN("SW_P71_ROBOT_USER_TURN", (byte)0x29),
    
    SW_SET_ODS_TEST_MODE("SW_SET_ODS_TEST_MODE", (byte)0x2a),
    SW_GET_ODS_DATA("SW_GET_ODS_DATA", (byte)0x2b),
    
    /* Learn Mode */
    SW_ENABLE_LEARN_MODE("SW_ENABLE_LEARN_MODE", (byte)0x2c),
    SW_DISABLE_LEARN_MODE("SW_DISABLE_LEARN_MODE", (byte)0x2d),
    SW_GET_CALIBRATION_STATUS("SW_GET_CALIBRATION_STATUS", (byte)0x2e),
    SW_CONTROL_24V_POWER("SW_CONTROL_24V_POWER", (byte)0x2F),
    SW_MANUAL_ADJUST("SW_MANUAL_ADJUST", (byte)0x30),
    
    /* Write cmd range 0x64 to 0xC7 */
    SW_BEGIN_CHARGING("SW_BEGIN_CHARGING", (byte)0x64),
    SW_STOP_CHARGING("SW_STOP_CHARGING", (byte)0x65),
    SW_MOVE_TO_LOCATION("SW_MOVE_TO_LOCATION", (byte)0x66),
    SW_LIFT_POD("SW_LIFT_POD", (byte)0x67),
    SW_DROP_POD("SW_DROP_POD", (byte)0x68),
    SW_ROTATE_POD("SW_ROTATE_POD", (byte)0x69),
    SW_SET_ROBOT_ID("SW_SET_ROBOT_ID", (byte)0x6A),
//    SW_EXECUTE_TASK("SW_EXECUTE_TASK", (byte)0x6B), //(deprecated)
    SW_NO_FLOOR_LIFT_POD("SW_NO_FLOOR_LIFT_POD", (byte)0x6C),
    SW_CMD_RESPONSE("SW_CMD_RESPONSE", (byte)0xFF),
    SW_CMD_COMPLETE("SW_CMD_COMPLET", (byte)0xE6);
    


    
    
        private final String name;
        private final byte value;
    
        private CommandType(String name, byte value)
        {
            this.name = name;
            this.value = (byte)value;
        }


        public byte getCommand() {
            return value;
        }

}
