package Controllers;
//class for identifying error codes and printing it in the window
//will also return a string containing the error code when called

public class ErrorChecker{

    public static String check(byte code){
        switch (code){
            case (byte)0xC8: System.out.println("ERROR: SW_CRC_ERROR (not invalid but unknown)");
            return "<br/> ERROR: SW_CRC_ERROR (not invalid but unknown)";
            case (byte)0xC9: System.out.println("ERROR: SW_UNKNOWN_CMD");
            return "<br/> ERROR: SW_UNKNOWN_COMMAND";
            case (byte)0xCA: System.out.println("ERROR: SW_DATA_NOT_READY");
            return "<br/> ERROR: SW_DATA_NOT_READY";
            case (byte)0xCB: System.out.println("ERROR: SW_CMD_NOT_IMPLEMENTED");
            return "<br/> ERROR: SW_CMD_NOT_IMPLEMENTED";
            case (byte)0xCC: System.out.println("ERROR: SW_INVALID_LS");
            return "<br/> ERROR: SW_INVALID_LS";
            case (byte)0xCD: System.out.println("ERROR: SW_INVALID_POD_ID");
            return "<br/> ERROR: SW_INVALID_POD_ID";
            case (byte)0xCE: System.out.println("ERROR: SW_CAMERA_DM_FAILED");
            return "<br/> ERROR: SW_CAMERA_DM_FAILED";
            case (byte)0xCF: System.out.println("ERROR: SW_UNABLE_TO_ROTATE");
            return "<br/> ERROR: SW_UNABLE_TO_ROTATE";
            case (byte)0xD1: System.out.println("ERROR: SW_CMD_ALREADY_RUNNING");
            return "<br/> ERROR: SW_CMD_ALREADY_RUNNING";
            case (byte)0xD2: System.out.println("ERROR: SW_LIFT_ALREADY_LOWERED");
            return "<br/> ERROR: SW_LIFT_ALREADY_LOWERED";
            case (byte)0xD3: System.out.println("ERROR: SW_LIFT_ALREADY_RAISED");
            return "<br/> ERROR: SW_LIFT_ALREADY_RAISED";
            case (byte)0xD4: System.out.println("ERROR: SW_CMD_IGNORED_SYSTEM_FAULT");
            return "<br/> ERROR: SW_CMD_IGNORED_SYSTEM_FAULT";
            case (byte)0xD5: System.out.println("ERROR: SW_LEARN_MODE_ALREADY_ENABLED");
            return "<br/> ERROR: SW_LEARN_MODE_ALREADY_ENABLED";
            case (byte)0xD6: System.out.println("ERROR: SW_LEARN_MODE_ALREADY_DISABLED");
            return "<br/> ERROR: SW_LEARN_MODE_ALREADY_DISABLED";
            case (byte)0xD7: System.out.println("ERROR: SW_BARCODE_ADJUSTMENT_FAILED");
            return "<br/> ERROR: SW_BARCODE_ADJUSTMENT_FAILED";
            case (byte)0xD8: System.out.println("ERROR: SW_OBTUSE_POD_ANGLE");
            return "<br/> ERROR: SW_OBTUSE_POD_ANGLE";
            case (byte)0xD9: System.out.println("ERROR: SW_OBTUSE_FLOOR_ANGLE");
            return "<br/> ERROR: SW_OBTUSE_FLOOR_ANGLE";
            case (byte)0xDA: System.out.println("ERROR: SW_SAFETY_TRIGGERED");
            return "<br/> ERROR: SW_SAFETY_TRIGGERED";
            case (byte)0xDB: System.out.println("ERROR: SW_FAILED_START_CHARGE");
            return "<br/> ERROR: SW_FAILED_START_CHARGE";
            case (byte)0xDC: System.out.println("ERROR: SW_FAILED_END_CHARGE");
            return "<br/> ERROR: SW_FAILED_END_CHARGE";
            case (byte)0xDD: System.out.println("ERROR: SW_CHARGER_CONNECT_FAIL");
            return "<br/> ERROR: SW_CHARGER_CONNECT_FAIL";
            case (byte)0xDE: System.out.println("ERROR: SW_CHARGE_FAILED_FIDUCIAL_FIND");
            return "<br/> ERROR: SW_CHARGE_FAILED_FIDUCIAL_FIND";
            case (byte)0xDF: System.out.println("ERROR: SW_FAILED_FIDUCIAL_AND_FAILED_CONNECT");
            return "<br/> ERROR: SW_FAILED_FIDUCIAL_AND_FAILED_CONNECT";
            case (byte)0xE0: System.out.println("ERROR: SW_FAILED_TO_ALIGN_TO_FLOOR_FIDUCIAL");
            return "<br/> ERROR: SW_FAILED_TO_ALIGN_TO_FLOOR_FIDUCIAL";
            case (byte)0xE1: System.out.println("ERROR: SW_FAILED_TO_ALIGN_TO_CEILING_FIDUCIAL");
            return "<br/> ERROR: SW_FAILED_TO_ALIGN_TO_CEILING_FIDUCIAL";
            case (byte)0xE2: System.out.println("ERROR: SW_FAILED_TO_START_MOTION");
            return "<br/> ERROR: SW_FAILED_TO_START_MOTION";
            case (byte)0xE3: System.out.println("ERROR: SW_FAILED_TO_READ_AFTER_STOPPING");
            return "<br/> ERROR: SW_FAILED_TO_READ_AFTER_STOPPING";
            case (byte)0xE4: System.out.println("ERROR: SW_FAILED_TO_COMPLETE_TRANSLATE_TASK");
            return "<br/> ERROR: SW_FAILED_TO_COMPLETE_TRANSLATE_TASK";
            case (byte)0xE5: System.out.println("ERROR: SW_FAILED_TO_REACH_CORRECT_WAYPOINT");
            return "<br/> ERROR: SW_FAILED_TO_REACH_CORRECT_WAYPOINT";
            case (byte)0xE6: System.out.println("ERROR: SW_FAILED_LOCK_WHEELS");
            return "<br/> ERROR: SW_FAILED_LOCK_WHEELS";
            case (byte)0xE7: System.out.println("ERROR: SW_TIMED_OUT_STATE_TRANSITION");
            return "<br/> ERROR: SW_TIMED_OUT_STATE_TRANSITION";
            case (byte)0xE8: System.out.println("ERROR: SW_COMPLEX_ROTATION_FAILED");
            return "<br/> ERROR: SW_COMPLEX_ROTATION_FAILED";
            case (byte)0xE9: System.out.println("ERROR: SW_MISSED_FIDUCIALS");
            return "<br/> ERROR: SW_MISSED_FIDUCIALS";
            case (byte)0xEA: System.out.println("ERROR: SW_UNEXPECTED_FIDUCIAL");
            return "<br/> ERROR: SW_UNEXPECTED_FIDUCIAL";
            case (byte)0xEB: System.out.println("ERROR: SW_MOTION_CARD_ERROR");
            return "<br/> ERROR: SW_MOTION_CARD_ERROR";
            case (byte)0xEC: System.out.println("ERROR: SW_INVALID_ARGS");
            return "<br/> ERROR: SW_INVALID_ARGS";
            case (byte)0xED: System.out.println("ERROR: SW_BOT_BUSY");
            return "<br/> ERROR: SW_BOT_BUSY";
            case (byte)0xEE: System.out.println("ERROR: SW_BOT_IN_ESTOP");
            return "<br/> ERROR: SW_BOT_IN_ESTOP";
            default :
            return "";
        }
    }
    
}

