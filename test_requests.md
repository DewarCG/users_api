# Requests

## It should response with OK status

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/users \
  --header 'content-type: application/json' \
  --header 'user-agent: vscode-restclient' \
  --data '{"name": "Juan Rodriguez","email": "juan2@rodrigudez.org","password": "h32","phones": [{"number": "1234567","citycode": "1","contrycode": "57"}]}'
```

## It should response with "Formato de correo incorrecto"

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/users \
  --header 'content-type: application/json' \
  --header 'user-agent: vscode-restclient' \
  --data '{"name": "Juan Rodriguez","email": "juan2rodrigudez.org","password": "h32","phones": []}'
```

## It should response with "Formato de contraseña incorrecto"

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/users \
  --header 'content-type: application/json' \
  --header 'user-agent: vscode-restclient' \
  --data '{"name": "Juan Rodriguez","email": "juan2@rodrigudez.org","password": "h","phones": []}'
```

## It should response with "Correo ya registrado"

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/users \
  --header 'content-type: application/json' \
  --header 'user-agent: vscode-restclient' \
  --data '{"name": "Juan Rodriguez","email": "juan2@rodrigudez.org","password": "h32","phones": []}'
```

## It should response with "Nombre de usuario obligatorio"

```bash
curl --request POST \
  --url http://localhost:8080/api/v1/users \
  --header 'content-type: application/json' \
  --header 'user-agent: vscode-restclient' \
  --data '{"name": "","email": "juan@rodrigudez.org","password": "h32","phones": []}'
```
