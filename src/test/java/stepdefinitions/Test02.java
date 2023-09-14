package stepdefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Test02 {
    static RequestSpecification spec;

    @Test
    public void test01(){
        //api/opdList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
        // dönen status code'un 200 oldugu
        // ve response message bilgisinin "Success" oldugu dogrulanmali

        String token="Rr8vU0Q6cKNtKp7X8Kn8NYeZXu5a6r";
        spec=new RequestSpecBuilder().setBaseUri("http://heallifehospital.com").build(); //Anasayfa olusturuldu
        spec.pathParams("pp1","api","pp2","opdList");

        String fullPath="/{pp1}/{pp2}";

        Response response=given()
                .contentType(ContentType.JSON)  //Gonderdigim veriler JSON fromatinda
                .spec(spec)   //Class level'da Olusturdugum spec isimli obje, base url ve parametreleri kullanacagim
                .headers("Authorization","Bearer "+token,  //Gerekli authorization bilgisi girisi
                        "Content-Type",ContentType.JSON
                        ,"Accept",ContentType.JSON) //Bana response'i JSON formatinda gonder
                .when()
               // .body(reqBody.toString()) //request icinde body varsa
                .log().all() //olusturdugumuz request'i toplu halde gormek icin
                .get(fullPath); //parametrelerle beraber request type girisi
        response.prettyPrint();






    }
}
