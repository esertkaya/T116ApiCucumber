package stepdefinitions;

import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Test01 {

   static RequestSpecification spec;

   public static void main(String[] args) {
         /*
https://restful-booker.herokuapp.com/booking url’ine asagidaki body’ye sahip bir POST
request gonderdigimizde
{
  “firstname” : “Ahmet”,
  “lastname” : “Bulut”,
  “totalprice” : 500,
  “depositpaid” : false,
  “bookingdates” : {
                    “checkin” : “2021-06-01”,
                    “checkout” : “2021-06-10”
                    },
   “additionalneeds” : “wi-fi”
}
            donen Response’un,
            status code’unun 200,
            ve content type’inin application-json, ve response body’sindeki
            “firstname”in,“Ahmet”, ve “lastname”in, “Bulut”,
            ve “totalprice”in,500,
            ve “depositpaid”in,false,
            ve “checkin” tarihinin 2021-06-01 ve “checkout” tarihinin 2021-06-10 ve “additionalneeds”in,“wi-fi” olduğunu test edin (edited)
     */

       spec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build(); //Anasayfa olusturuldu
       spec.pathParams("pp1","booking");

       String fullPath="/{pp1}"; //Parametre girisi icin kolay bir string olusturuldu

       JSONObject reqBody=new JSONObject();  //Ana request body olusturuldu
       JSONObject innerBody=new JSONObject();
       innerBody.put("checkin","2021-06-01");
       innerBody.put("checkout","2021-06-10");

       reqBody.put("firstname","Ahmet");
       reqBody.put("lastname","Bulut");
       reqBody.put("totalprice",500);
       reqBody.put("depositpaid",false);
       reqBody.put("bookingdates",innerBody);
       reqBody.put("additionalneeds","wi-fi");

       Response response=given()
               .contentType(ContentType.JSON)  //Gonderdigim veriler JSON fromatinda
               .spec(spec)   //Class level'da Olusturdugum spec isimli obje, base url ve parametreleri kullanacagim
               .headers("Content-Type",ContentType.JSON
                       ,"Accept",ContentType.JSON) //Bana response'i JSON formatinda gonder
               .when()
               .body(reqBody.toString()) //request icinde body varsa
               .log().all() //olusturdugumuz request'i toplu halde gormek icin
               .post(fullPath); //parametrelerle beraber request type girisi
       response.prettyPrint();
       /*
        donen Response’un,
            status code’unun 200,
            ve content type’inin application-json, ve response body’sindeki
            “firstname”in,“Ahmet”, ve “lastname”in, “Bulut”,
            ve “totalprice”in,500,
            ve “depositpaid”in,false,
            ve “checkin” tarihinin 2021-06-01 ve “checkout” tarihinin 2021-06-10 ve “additionalneeds”in,“wi-fi” olduğunu test edin (edited)
        */

response
        .then()
        .assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("booking.firstname", Matchers.equalTo("Ahmet"))
        .body("booking.lastname", Matchers.equalTo("Bulut"))
        .body("booking.totalprice", Matchers.equalTo(500))
        .body("booking.depositpaid", Matchers.equalTo(false))
        .body("booking.bookingdates.checkin", Matchers.equalTo("2021-06-01"))
        .body("booking.bookingdates.checkout", Matchers.equalTo("2021-06-10"))
        .body("booking.additionalneeds",equalTo("wi-fi"));


    }
}
