# retrofitDemo

## 사용방법

```bash
$ ./gradlew run


$ curl -XGET localhost:8080/internal/persons
[{"name":"teddy","age":10},{"name":"bono","age":10},{"name":"land","age":10},{"name":"eden","age":10}]

$ curl -XPOST localhost:8080/internal/person?name=teddy
{"name":"teddy","age":10}
```
