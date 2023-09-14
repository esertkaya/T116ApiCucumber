Feature: api/opdList
  Scenario: api/opdList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

    * Api kullanicisi sisteme admin olarak giris yapar
    * Api kullanicisi url ile birlikte iki parametreli giris yapar parametreler "api" ve "opdList"
    *