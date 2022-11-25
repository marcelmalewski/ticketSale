
## rest-api

### deploy

```shell 
helm upgrade --install rest-api --namespace sii -f ./values.yaml .

```

### list 

```shell 
helm list

```

### purge 

```shell 
helm uninstall rest-api --namespace sii

```