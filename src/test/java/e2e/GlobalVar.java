package e2e;

import com.demo.testng.program.model.RequestModel.*;
import com.demo.testng.program.model.ResponseModel.*;


public class GlobalVar {
    public final static String BASE_URL = "https://whitesmokehouse.com";

    public static String token;
    public static String email;
    public static String password;
    public static String fullName;
    public static String department;
    public static String phonenumber;

    public static int objectid;
    public static String objectname;
    public static String year;
    public static double price;
    public static String cpu_model;
    public static String hard_disk_size;
    public static String capacity;
    public static String screen_size;
    public static String color;

    public static RequestRegister requestRegister;
    public static RequestAddObject requestAddObject;
    public static RequestAddObject.DataDetails addDataDetails;
    public static RequestUpdateObject requestUpdateObject;
    public static RequestUpdateObject.DataDetails requestUpdateDataDetails;
    public static RequestPartiallyUpdateObject requestPartiallyUpdateObject;

    public static RegisterResponse registerResponse;
    public static AddObjectResponse addObjectResponse;
    public static AddObjectResponse.DataDetails dataDetailsResponse;
    public static GetObjectbyIDResponse getObjectbyIDResponse;
    public static GetObjectbyIDResponse.DataDetails dataDetailsGetObjectID;
    public static UpdateObjectResponse updateObjectResponse;
    public static UpdateObjectResponse.DataDetails updateDataDetailsResponse; 
    public static PartiallyUpdateObjectResponse partiallyUpdateObjectResponse;
    public static DeleteObjectResponse deleteObjectResponse;
}
