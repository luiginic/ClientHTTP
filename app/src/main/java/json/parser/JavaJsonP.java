//
//package json.parser;
//
//
 /*
    Chisa Daniel
*/


//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONParser;
//import org.json.ParseException;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//
///**
// *
// * @author acer
// */
//public class JavaJsonP {
//
//       static private final  String filePath = "C:\\Users\\acer\\Desktop\\test.json";
//    /**
//     * @param args the command line arguments
//     */
//    public void parse(JSONObject jsonObject)
//    {
//        // TODO code application logic here
//        JSONParser jsonParser = new JSONParser();
//        try {
//            // read the json file
//            FileReader reader = new FileReader(filePath);
//
//
//            jsonObject = (JSONObject) jsonParser.parse(reader);
//
//            // get a String from the JSON object
//            String firstName = (String) jsonObject.get("firstname");
//            System.out.println("The first name is: " + firstName);
//
//            String lastName = (String) jsonObject.get("lastname");
//            System.out.println("The last name is: " + lastName);
//
//            String cnp = (String) jsonObject.get("cnp");
//            System.out.println("The cnp is: " + cnp);
//
//             String weight = (String) jsonObject.get("weight");
//            System.out.println("The weight is: " + weight + "kg");
//
//             String height = (String) jsonObject.get("height");
//            System.out.println("The cnp is: " + height + "m");
//
//            // get a number from the JSON object
//            String id =  (String) jsonObject.get("age");
//            System.out.println("Date of birth: " + id);
//
//            // get an array from the JSON object
//            JSONArray lang= (JSONArray) jsonObject.get("diseas");
//
//            // take the elements of the json array
//            for(int i=0; i<lang.size(); i++){
//                System.out.println("patient's illness "+i+": "+lang.get(i));
//            }
//
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (NullPointerException ex) {
//            ex.printStackTrace();
//        }   catch (ParseException ex) {
//                Logger.getLogger(JavaJsonP.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//    }
//
//
//
//    }
    



