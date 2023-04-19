#### Тесты на API demo-магазина https://demoqa.com/swagger/#/
![swagger demoqa](https://user-images.githubusercontent.com/118796374/233045535-24212bff-94c6-441d-8840-d0aca56b5802.png)

Реализован тест на метод авторизации с описанием спецификации:
```bash
POST
https://demoqa.com/Account/v1/Authorized1
```
Так же, реализованы тесты на метод получения списка книг с описанием спецификации,

```bash
GET
https://demoqa.com/BookStore/v1/Books
```
в которых осуществляется проверка соответствия в ответе данных первой в списке книги с помощью библиотеки Lombok: 
```bash
"books": [
    {
      "isbn": "9781449325862",
      "title": "Git Pocket Guide",
      "subTitle": "A Working Introduction",
      "author": "Richard E. Silverman",
      "publish_date": "2020-06-04T08:48:39.000Z",
      "publisher": "O'Reilly Media",
      "pages": 234,
      "description": "This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp",
      "website": "http://chimera.labs.oreilly.com/books/1230000000561/index.html"
    }
 ```
и при помощи Groovy проверяется наличие книги с автором "Axel Rauschmayer" :
```bash
{
      "isbn": "9781449365035",
      "title": "Speaking JavaScript",
      "subTitle": "An In-Depth Guide for Programmers",
      "author": "Axel Rauschmayer",
      "publish_date": "2014-02-01T00:00:00.000Z",
      "publisher": "O'Reilly Media",
      "pages": 460,
      "description": "Like it or not, JavaScript is everywhere these days-from browser to server to mobile-and now you, too, need to learn the language or dive deeper than you have. This concise book guides you into and through JavaScript, written by a veteran programmer who o",
      "website": "http://speakingjs.com/"
    }
 ```
